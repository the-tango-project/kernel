package org.apeiron.kernel.service.actionable;

import java.util.Map;
import org.apeiron.kernel.service.dto.ActionDto;
import org.apeiron.kernel.service.dto.ContextDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActionEngine implements Actionable {

    @Autowired
    private Map<String, IAction> actions;

    @Override
    public Mono<ContextDto> execute(ContextDto contexto) {
        contexto.getSolicitud().setEstado(contexto.getTransicion().getDestino());
        Flux<ActionDto> currentActions = Flux.fromIterable(contexto.getTransicion().getAcciones());
        return currentActions.flatMap(action -> actions.get(action.getClave()).execute(contexto)).then(Mono.just(contexto));
    }
}
