package org.apeiron.kernel.service.actionable;

import java.util.Map;
import org.apeiron.kernel.service.dto.ActionDTO;
import org.apeiron.kernel.service.dto.ContextDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActionEngine implements Actionable {

    @Autowired
    private Map<String, IAction> actions;

    @Override
    public Mono<ContextDTO> execute(ContextDTO contexto) {
        contexto.getSolicitud().setEstado(contexto.getTransicion().getDestino());
        Flux<ActionDTO> currentActions = Flux.fromIterable(contexto.getTransicion().getAcciones());
        return currentActions.flatMap(action -> actions.get(action.getClave()).execute(contexto)).then(Mono.just(contexto));
    }
}
