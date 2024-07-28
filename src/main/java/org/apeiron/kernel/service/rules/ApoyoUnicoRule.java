package org.apeiron.kernel.service.rules;

import static java.util.Objects.isNull;

import org.apeiron.kernel.autoconfiguration.Constants.RuleTag;
import org.apeiron.kernel.commons.annotations.ApeironRule;
import org.apeiron.kernel.commons.annotations.Condition;
import org.apeiron.kernel.commons.annotations.Tags;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.service.SolicitudService;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.dto.SolicitudDto;
import org.apeiron.kernel.service.validator.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

/**
 * The `ApoyoUnicoRule` class implements the `IRule` interface and validates a
 * context based on a condition involving a `SolicitudService` instance.
 */
@ApeironRule(nombre = "Solicitud única por convocatoria")
@Condition("Un solicitante sólo puede enviar una solicitud por convocatoria")
@Tags({ RuleTag.Must.APOYO_UNICO })
public class ApoyoUnicoRule implements IRule {

    @Autowired
    private SolicitudService solicitudService;

    @Override
    public Mono<Boolean> validate(ContextDto context) {
        return solicitudService
                .findBySolucionIdAndEstado(context.getSolucion().getId(), EstadoSolicitud.ENVIADA)
                .switchIfEmpty(Mono.just(new SolicitudDto()))
                .flatMap(solucion -> Mono.just(isNull(solucion.getId())));
    }
}
