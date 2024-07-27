package org.apeiron.kernel.web.rest;

import java.net.URISyntaxException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.service.ResultadoService;
import org.apeiron.kernel.service.dto.ResultadoDto;
import org.apeiron.kernel.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link org.apeiron.kernel.domain.Form}.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResultadoResource {

    private final Logger log = LoggerFactory.getLogger(ResultadoResource.class);

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "Resultado";

    private final ResultadoService resultadoService;

    /**
     * {@code PUT  /Resultados/:id} : Updates an existing Resultado.
     *
     * @param id           the id of the ResultadoDto to save.
     * @param ResultadoDto the ResultadoDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated ResultadoDto,
     *         or with status {@code 400 (Bad Request)} if the ResultadoDto is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         ResultadoDto
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resultados/{id}")
    public Mono<ResponseEntity<ResultadoDto>> updateResultado(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResultadoDto resultadoDto
    ) throws URISyntaxException {
        log.debug("REST request to update Resultado : {}, {}", id, resultadoDto);
        if (resultadoDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resultadoDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return resultadoService
            .save(resultadoDto)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .map(result ->
                ResponseEntity
                    .ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                    .body(result)
            );
    }

    /**
     * {@code GET  /resultados/:id} : get the "id" resultados.
     *
     * @param id the id of solicitud id.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the resultadoDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resultados/{id}")
    public Mono<ResponseEntity<ResultadoDto>> getResultado(@PathVariable String id) {
        log.debug("REST request to get Resultado : {}", id);
        Mono<ResultadoDto> resultadoDto = resultadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resultadoDto);
    }
}
