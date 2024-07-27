package org.apeiron.kernel.service.actions;

import org.apeiron.kernel.commons.annotations.ApeironAction;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.ContextDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@ApeironAction(nombre = "Enviar notificacion")
public class EnviarNotificacion implements IAction {

    private final Logger log = LoggerFactory.getLogger(EnviarNotificacion.class);

    @Override
    public Mono<Void> execute(ContextDto context) {
        log.debug("Enviar notificacion : {}, {}", context.getSolicitud().getId());
        return Mono.empty().then();
    }
}
