package org.apeiron.kernel.web.rest;

import org.apeiron.kernel.service.jobrunr.JobStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST controller job status
 */
@RestController
@RequestMapping("/api")
public class JobApiResource {

    @Autowired
    private JobStatusService jobStatusService;

    /**
     * Recupera el estado del job de jobrunr
     *
     * @param id ID del job en jobrunr
     * @return
     */
    @GetMapping("/job/{id}")
    public Mono<ResponseEntity<String>> etjobStatus(@PathVariable(value = "id", required = true) String id) {
        return Mono.just(ResponseEntity.ok(jobStatusService.jobStatus(id).toString()));
    }
}
