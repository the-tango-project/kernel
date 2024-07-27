package org.apeiron.kernel.service.impl;

import static org.apeiron.kernel.service.util.QueryHelper.buildSolicitudQuery;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.repository.SolicitudRepository;
import org.apeiron.kernel.security.SecurityUtils;
import org.apeiron.kernel.service.SolicitudService;
import org.apeiron.kernel.service.dto.SolicitudDto;
import org.apeiron.kernel.service.exception.NotFoundException;
import org.apeiron.kernel.service.mapper.SolicitudMapper;
import org.apeiron.kernel.service.util.Filtro;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Solicitud}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final SolicitudMapper solicitudMapper;

    @Override
    public Mono<SolicitudDto> save(SolicitudDto solicitudDto) {
        log.debug("Request to save Solicitud : {}", solicitudDto);
        return SecurityUtils
                .getCurrentUserLogin()
                .switchIfEmpty(Mono.just("anonymous"))
                .map(usuario -> solicitudMapper.toEntity(solicitudDto.usuario(usuario)))
                .flatMap(solicitudRepository::save)
                .map(solicitudMapper::toDto);
    }

    @Override
    public Mono<SolicitudDto> migrar(SolicitudDto solicitudDto) {
        log.debug("Request to save Solicitud : {}", solicitudDto);
        return solicitudRepository.save(solicitudMapper.toEntity(solicitudDto)).map(solicitudMapper::toDto);
    }

    @Override
    public Mono<SolicitudDto> update(SolicitudDto solicitudDto) {
        log.debug("Request to update Solicitud : {}", solicitudDto);
        // TODO CORREGIR: AL GUARDAR PROPERTIES, LOS FORMATOS DE FECHA SE PIERDEN Y
        // QUEDAN COMO STRING
        return solicitudRepository.save(solicitudMapper.toEntity(solicitudDto)).map(solicitudMapper::toDto);
    }

    @Override
    public Mono<SolicitudDto> partialUpdate(SolicitudDto solicitudDto) {
        log.debug("Request to partially update Solicitud : {}", solicitudDto);

        return solicitudRepository
                .findById(solicitudDto.getId())
                .map(existingSolicitud -> {
                    solicitudMapper.partialUpdate(existingSolicitud, solicitudDto);

                    return existingSolicitud;
                })
                .flatMap(solicitudRepository::save)
                .map(solicitudMapper::toDto);
    }

    @Override
    public Flux<SolicitudDto> findAll(Filtro filtro) {
        log.debug("Request to get all Solicituds by filter");

        return solicitudRepository.findAll(buildSolicitudQuery(filtro)).map(solicitudMapper::toDto);
    }

    @Override
    public Flux<SolicitudDto> findAll(Filtro filtro, Pageable pageable) {
        log.debug("Request to get all Solicituds");

        return solicitudRepository
                .findAll(buildSolicitudQuery(filtro), pageable.getSort())
                .skip(pageable.getOffset())
                .take(pageable.getPageSize())
                .map(solicitudMapper::toDto);
    }

    public Flux<SolicitudDto> findAllInvitaciones() {
        return SecurityUtils
                .getCurrentUserLogin()
                .switchIfEmpty(Mono.just("anonymous"))
                .flatMap(user -> {
                    Flux<Solicitud> sol = null;
                    sol = solicitudRepository.findAllByRevisoresIn(user);
                    Flux<SolicitudDto> solDto = sol.map(solicitudMapper::toDto);
                    return solDto.collectList();
                })
                .flatMapIterable(re -> re);
    }

    @Override
    public Mono<Long> countAll() {
        return solicitudRepository.count();
    }

    @Override
    public Mono<Long> countAll(Filtro filtro) {
        return solicitudRepository.count(buildSolicitudQuery(filtro));
    }

    @Override
    public Mono<SolicitudDto> findOne(String id) {
        log.debug("Request to get Solicitud : {}", id);
        return solicitudRepository.findById(id).map(solicitudMapper::toDto);
    }

    @Override
    public Mono<SolicitudDto> findBySolucionId(String id) {
        log.debug("Request to get Solicitud : {}", id);
        return SecurityUtils
                .getCurrentUserLogin()
                .switchIfEmpty(Mono.just("anonymous"))
                .flatMap(usuario -> solicitudRepository.findFirstByUsuarioAndSolucionId(usuario, id))
                .map(solicitudMapper::toDto);
    }

    @Override
    public Mono<SolicitudDto> findBySolucionIdAndEstado(String id, EstadoSolicitud estado) {
        log.debug("Request to get Solicitud : {}", id);
        return SecurityUtils
                .getCurrentUserLogin()
                .switchIfEmpty(Mono.just("anonymous"))
                .flatMap(usuario -> solicitudRepository.findByUsuarioAndSolucionIdAndEstado(usuario, id, estado))
                .map(solicitudMapper::toDto);
    }

    @Override
    public Mono<SolicitudDto> findRevisoresById(String id) {
        return solicitudRepository.findRevisoresById(id).map(solicitudMapper::toDto);
    }

    @Override
    public Mono<SolicitudDto> updateRevisores(SolicitudDto solicitudDto) {
        log.debug("Request to update Solicitud : {}", solicitudDto);
        return solicitudRepository
                .findById(solicitudDto.getId())
                .flatMap(solicitud -> {
                    Solicitud currentSolicitud = solicitudMapper.toEntity(solicitudDto);
                    solicitud.setRevisores(currentSolicitud.getRevisores());
                    return solicitudRepository.save(solicitud);
                })
                .map(solicitudMapper::toDto);
    }

    /**
     * Consulta la lista de solicitudes asociadas a solucionId y usuario logueado
     * 
     * @param solucionId
     * @param pageable
     * @return Flux<SolicitudDto>
     */
    @Override
    public Flux<SolicitudDto> findAllByUsuarioAndSolucionId(String solucionId, Pageable pageable) {
        log.debug("Request get List Solicitudes by solucionId: {} ", solucionId);
        return SecurityUtils
                .getCurrentUserLogin()
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró el usuario")))
                .flatMapMany(usuarioSesion -> solicitudRepository.findAllByUsuarioAndSolucionId(usuarioSesion,
                        solucionId, pageable))
                .map(solicitudMapper::toDto);
    }

    /**
     * Consulta la lista de solicitudes asociadas a la lista de solucionIds y
     * usuario logueado
     * 
     * @param solucionIds
     * @param pageable
     * @return Flux<SolicitudDto>
     */
    @Override
    public Flux<SolicitudDto> findAllByAllSoluciones(List<String> solucionIds, Pageable pageable) {
        return SecurityUtils
                .getCurrentUserLogin()
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró el usuario")))
                .flatMapMany(usuarioSesion -> solicitudRepository
                        .findAllByAllSoluciones(usuarioSesion, solucionIds)
                        .onErrorResume(e -> {
                            throw new NotFoundException("No se encontraron resultados.");
                        }))
                .map(solicitudMapper::toDto);
    }

    @Override
    public Mono<Boolean> exists(Filtro filtro) {
        return solicitudRepository.exists(buildSolicitudQuery(filtro));
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Solicitud : {}", id);
        return solicitudRepository.deleteById(id);
    }
}
