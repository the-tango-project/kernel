package org.apeiron.kernel.service.validator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apeiron.kernel.service.dto.factories.RulesExceptionFactory.invalidTransitionByAction;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.TipoAccion;
import org.apeiron.kernel.service.dto.ComponenteDTO;
import org.apeiron.kernel.service.dto.ContextDTO;
import org.apeiron.kernel.service.dto.EstadoDTO;
import org.apeiron.kernel.service.dto.RuleDTO;
import org.apeiron.kernel.service.dto.SolucionDTO;
import org.apeiron.kernel.service.dto.TransicionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ValidatorEngine implements Validator {

    @Autowired
    private Map<String, IRule> rules;

    @Override
    public Mono<Result> validate(ContextDTO contexto) {
        try {
            ValidatorEngine.resolveState(contexto);
            ValidatorEngine.resolveTransition(contexto);
        } catch (Exception ex) {
            log.error("Error al realizar la validación de reglas", ex);
            return Mono.error(invalidTransitionByAction(contexto.getAccion()));
        }

        if (isNull(contexto.getTransicion())) {
            return Mono.error(invalidTransitionByAction(contexto.getAccion()));
        }

        Flux<RuleDTO> currentRules = Flux.fromIterable(contexto.getTransicion().getReglas());

        return currentRules
            .filterWhen(rule -> filterRules(rule, contexto))
            .collectList()
            .map(errores -> Result.builder().contexto(contexto).errores(errores).build());
    }

    @Override
    public Mono<Result> validateForm(ContextDTO contexto) {
        Flux<ComponenteDTO> componentes = Flux.fromIterable(contexto.getSolucion().getComponentes());
        return componentes
            .filter(componente -> componente.getFormId().equals(contexto.getFormId()))
            .flatMap(componente -> Flux.fromIterable(componente.getConfiguracion().getReglas()))
            .filterWhen(rule -> filterRules(rule, contexto))
            .collectList()
            .map(errores -> Result.builder().contexto(contexto).errores(errores).build());
    }

    private Mono<Boolean> filterRules(RuleDTO rule, ContextDTO contexto) {
        if (!nonNull(contexto.getSolicitud().getId())) {
            return Mono.just(true);
        }
        return rules.get(rule.getClave()).validate(contexto).map(isValid -> !isValid);
    }

    private static void resolveState(ContextDTO contexto) {
        SolucionDTO solucion = contexto.getSolucion();
        EstadoSolicitud currentState = contexto.getSolicitud().getEstado();
        for (EstadoDTO estado : solucion.getProceso().getEstados()) {
            if (estado.getNombre().equals(currentState)) {
                contexto.setEstadoActual(estado);
            }
        }
    }

    private static void resolveTransition(ContextDTO contexto) {
        TipoAccion accion = contexto.getAccion();
        for (TransicionDTO transicion : contexto.getEstadoActual().getTransiciones()) {
            if (transicion.getAccion().equals(accion)) {
                contexto.setTransicion(transicion);
            }
        }
    }
}
