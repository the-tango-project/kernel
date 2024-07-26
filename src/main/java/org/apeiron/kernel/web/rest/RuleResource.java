package org.apeiron.kernel.web.rest;

import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.commons.annotations.AnnotationProcessor;
import org.apeiron.kernel.service.dto.RuleDTO;
import org.apeiron.kernel.service.validator.IRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
public class RuleResource {

    private final Logger log = LoggerFactory.getLogger(RuleResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final List<IRule> reglas;

    @GetMapping("/rules")
    public Mono<ResponseEntity<List<RuleDTO>>> getAllRules(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Rules");

        return Flux
            .fromIterable(reglas)
            .map(AnnotationProcessor::resolveRule)
            .sort(Comparator.comparing(RuleDTO::getNombre))
            .collectList()
            .map(regla -> ResponseEntity.ok().body(regla));
    }
}
