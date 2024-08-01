package org.apeiron.kernel.service.impl;

import static org.apeiron.kernel.configuration.Constants.Apeiron.VALID_SOLICITUD_STATE;
import static org.apeiron.kernel.service.dto.factories.BulkDataFactory.failure;
import static org.apeiron.kernel.service.dto.factories.BulkDataFactory.success;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.security.SecurityUtils;
import org.apeiron.kernel.service.BitacoraService;
import org.apeiron.kernel.service.ComentarioService;
import org.apeiron.kernel.service.JobService;
import org.apeiron.kernel.service.NotificacionService;
import org.apeiron.kernel.service.PerfilService;
import org.apeiron.kernel.service.ProcesoService;
import org.apeiron.kernel.service.SolicitudService;
import org.apeiron.kernel.service.SolucionService;
import org.apeiron.kernel.service.actionable.ActionEngine;
import org.apeiron.kernel.service.dto.BulkDataProcessResultDto;
import org.apeiron.kernel.service.dto.BulkResponseDto;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.dto.NotificacionContext;
import org.apeiron.kernel.service.dto.SolicitudDto;
import org.apeiron.kernel.service.dto.SolucionDto;
import org.apeiron.kernel.service.dto.TransicionContextDto;
import org.apeiron.kernel.service.dto.factories.BitacoraFactory;
import org.apeiron.kernel.service.dto.factories.BulkDataFactory;
import org.apeiron.kernel.service.dto.factories.ComentarioFactory;
import org.apeiron.kernel.service.exception.RuleExceptionFactory;
import org.apeiron.kernel.service.exception.RulesException;
import org.apeiron.kernel.service.util.Filtro;
import org.apeiron.kernel.service.validator.Result;
import org.apeiron.kernel.service.validator.ValidatorEngine;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * Service Implementation for managing {@link Solicitud}.
 */
@Service
@RequiredArgsConstructor
public class ProcesoServiceImpl implements ProcesoService {

    private final ValidatorEngine validator;

    private final ActionEngine actionable;

    private final SolucionService solucionService;

    private final SolicitudService solicitudService;

    private final BitacoraService bitacoraService;

    private final ComentarioService comentarioService;

    private final JobService jobService;

    private final NotificacionService notificacionService;

    private final PerfilService miPerfilService;

    @Override
    public Mono<SolicitudDto> doTransicion(TransicionContextDto transicion) {
        String solicitudId = Optional.ofNullable(transicion.getSolicitudId()).orElse("invalid-id");
        String solucionId = Optional.ofNullable(transicion.getSolucionId()).orElse("invalid-solucion");

        return solicitudService
            .findOne(solicitudId)
            .switchIfEmpty(Mono.just(new SolicitudDto()))
            .zipWith(solucionService.findOne(solucionId))
            .flatMap(merge -> createContext(transicion, merge))
            .flatMap(validator::validate)
            .flatMap(this::doActions);
    }

    @Override
    public Mono<BulkResponseDto> doMultipleTransicions(List<TransicionContextDto> transicions) {
        return Flux.fromIterable(transicions).flatMap(this::processTransition).collectList().map(BulkDataFactory::response);
    }

    @Override
    public Mono<BulkResponseDto> doMultipleCreations(List<SolicitudDto> solicitudes) {
        return Flux.fromIterable(solicitudes).flatMap(this::processSolicitudes).collectList().map(BulkDataFactory::response);
    }

    private Mono<BulkDataProcessResultDto> processSolicitudes(SolicitudDto solicitud) {
        AtomicReference<BulkDataProcessResultDto> creationResult = new AtomicReference<>();
        var solicitudDto = miPerfilService
            .getPerfil(solicitud.getSolicitante().getCvu())
            .zipWith(
                solicitudService
                    .findAll(
                        Filtro.builder().solucionId(solicitud.getSolucionId()).cvuSolicitante(solicitud.getSolicitante().getCvu()).build()
                    )
                    .collectList()
            )
            .flatMap(merge -> {
                if (!filterValidState(merge.getT2()).isEmpty()) {
                    return Mono.error(RuleExceptionFactory.alreadyExist());
                }
                return merge
                    .getT1()
                    .map(solicitanteDto -> {
                        solicitud.setSolicitante(solicitanteDto);
                        solicitud.setUsuario(solicitud.getSolicitante().getLogin());
                        solicitud.getProperties().put("fechaRegistro", Instant.now().toString());
                        return solicitud;
                    });
            })
            .flatMap(this.solicitudService::migrar)
            .doOnSuccess(sol -> creationResult.set(success(solicitud)))
            .doOnError(exception -> creationResult.set(failure(solicitud, exception)))
            .onErrorReturn(new SolicitudDto());

        return solicitudDto.map(sol -> creationResult.get());
    }

    private List<SolicitudDto> filterValidState(List<SolicitudDto> solicitudes) {
        return solicitudes
            .stream()
            .filter(solicitud -> !VALID_SOLICITUD_STATE.contains(solicitud.getEstado()))
            .collect(Collectors.toList());
    }

    private Mono<BulkDataProcessResultDto> processTransition(TransicionContextDto transicion) {
        AtomicReference<BulkDataProcessResultDto> transitionResult = new AtomicReference<>();

        var solicitudDto =
            this.doTransicion(transicion)
                .doOnSuccess(solicitud -> transitionResult.set(success(transicion)))
                .doOnError(exception -> transitionResult.set(failure(transicion, exception)))
                .onErrorReturn(new SolicitudDto());
        return solicitudDto.map(solicitud -> transitionResult.get());
    }

    @Override
    public Mono<SolicitudDto> saveForm(SolicitudDto solicitud, String formId) {
        return solucionService
            .findOne(solicitud.getSolucionId())
            .flatMap(solucion -> createContext(solicitud, solucion, formId))
            .flatMap(validator::validateForm)
            .flatMap(this::doSaveForm);
    }

    private Mono<ContextDto> createContext(TransicionContextDto transicion, Tuple2<SolicitudDto, SolucionDto> merge) {
        return SecurityUtils
            .getCurrentUserLogin()
            .switchIfEmpty(Mono.just("system"))
            .map(usuario -> {
                ContextDto context = ContextDto
                    .builder()
                    .accion(transicion.getTransicion().getAccion())
                    .inicioTransicion(Instant.now())
                    .transicionContext(transicion)
                    .solicitud(merge.getT1())
                    .solucion(merge.getT2())
                    .usuario(usuario)
                    .build();
                transicion.getProperties().forEach((k, v) -> context.getSolicitud().getProperties().put(k, v));
                return context;
            });
    }

    private Mono<ContextDto> createContext(SolicitudDto solicitud, SolucionDto solucion, String formId) {
        return SecurityUtils
            .getCurrentUserLogin()
            .switchIfEmpty(Mono.just("system"))
            .flatMap(usuario ->
                Mono.just(
                    ContextDto
                        .builder()
                        .inicioTransicion(Instant.now())
                        .solicitud(solicitud)
                        .solucion(solucion)
                        .usuario(usuario)
                        .formId(formId)
                        .build()
                )
            );
    }

    private Mono<SolicitudDto> doActions(Result result) {
        if (!result.getErrores().isEmpty()) {
            return Mono.error(new RulesException("solucionId", result.getErrores()));
        }
        return actionable
            .execute(result.getContexto())
            .flatMap(this::saveSolicitud)
            .flatMap(this::doPostActions)
            .flatMap(contexto -> Mono.just(contexto.getSolicitud()));
    }

    private Mono<SolicitudDto> doSaveForm(Result result) {
        if (!result.getErrores().isEmpty()) {
            return Mono.error(new RulesException("solucionId", result.getErrores()));
        }
        return this.saveSolicitud(result.getContexto()).flatMap(contexto -> Mono.just(contexto.getSolicitud()));
    }

    private Mono<ContextDto> saveSolicitud(ContextDto contexto) {
        return solicitudService
            .update(contexto.getSolicitud())
            .map(solicitud -> {
                contexto.setSolicitud(solicitud);
                return contexto;
            });
    }

    private Mono<ContextDto> doPostActions(ContextDto contexto) {
        if (contexto.getTransicion().isAgregarComentario()) {
            jobService.schedule(() -> comentarioService.saveAsynchronous(ComentarioFactory.fromContext(contexto)));
        }
        if (contexto.getTransicion().getNotificacion().isActivada()) {
            notificacionService.send(
                NotificacionContext
                    .builder()
                    .mailTemplate(contexto.getSolucion().getMailTemplate())
                    .notificacion(contexto.getTransicion().getNotificacion())
                    .solicitud(contexto.getSolicitud())
                    .build()
            );
        }
        jobService.schedule(() -> bitacoraService.saveAsynchronous(BitacoraFactory.fromContext(contexto)));
        return Mono.just(contexto);
    }
}
