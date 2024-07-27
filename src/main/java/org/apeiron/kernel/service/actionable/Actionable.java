package org.apeiron.kernel.service.actionable;

import org.apeiron.kernel.service.dto.ContextDto;
import reactor.core.publisher.Mono;

public interface Actionable {
    public Mono<ContextDto> execute(ContextDto contexto);
}
