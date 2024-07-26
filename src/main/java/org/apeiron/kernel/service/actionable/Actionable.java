package org.apeiron.kernel.service.actionable;

import org.apeiron.kernel.service.dto.ContextDTO;
import reactor.core.publisher.Mono;

public interface Actionable {
    public Mono<ContextDTO> execute(ContextDTO contexto);
}
