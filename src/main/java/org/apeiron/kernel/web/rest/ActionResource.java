package org.apeiron.kernel.web.rest;

import java.util.List;
import org.apeiron.kernel.commons.annotations.AnnotationProcessor;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.ActionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing {@link org.apeiron.kernel.domain.Rule}.
 */
@RestController
@RequestMapping("/api")
public class ActionResource {

    private final Logger log = LoggerFactory.getLogger(ActionResource.class);

    // FIXME: Quitar del modelo principal
    @Value("${kernel.clientApp.name}")
    private String applicationName;

    @Autowired
    private List<IAction> actions;

    /**
     * This method `getAllActions` in the `ActionResource` class is a REST endpoint
     * that handles a GETrequest to retrieve a list of actions. Here's a breakdown
     * of what it does:
     * 
     * @param pageable
     * @param request
     * @return the list of actionsDto
     */
    @GetMapping("/actions")
    public Mono<ResponseEntity<List<ActionDto>>> getAllActions(
            @org.springdoc.core.annotations.ParameterObject Pageable pageable,
            ServerHttpRequest request) {
        log.debug("REST request to get a page of Actions");

        return Flux
                .fromIterable(actions)
                .map(AnnotationProcessor::resolveActions)
                .collectList()
                .map(action -> ResponseEntity.ok().body(action));
    }
}
