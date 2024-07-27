package org.apeiron.kernel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.apeiron.kernel.domain.enumeration.EstadoSolucion;
import org.apeiron.kernel.repository.SolucionRepository;
import org.apeiron.kernel.service.SolucionService;
import org.apeiron.kernel.service.dto.DefinicionEvaluacionDto;
import org.apeiron.kernel.service.dto.SolucionDto;
import org.apeiron.kernel.service.util.Filtro;
import org.apeiron.kernel.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing
 * {@link org.apeiron.kernel.domain.Solucion}.
 */
@RestController
@RequestMapping("/api")
public class SolucionResource {

    private final Logger log = LoggerFactory.getLogger(SolucionResource.class);

    private static final String ENTITY_NAME = "solucion";

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    private final SolucionService solucionService;

    private final SolucionRepository solucionRepository;

    public SolucionResource(SolucionService solucionService, SolucionRepository solucionRepository) {
        this.solucionService = solucionService;
        this.solucionRepository = solucionRepository;
    }

    /**
     * {@code POST  /solucions} : Create a new solucion.
     *
     * @param solucionDto the solucionDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new solucionDto, or with status {@code 400 (Bad Request)} if
     *         the solucion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/soluciones")
    public Mono<ResponseEntity<SolucionDto>> createSolucion(@RequestBody SolucionDto solucionDto) throws URISyntaxException {
        log.debug("REST request to save Solucion : {}", solucionDto);
        if (solucionDto.getId() != null) {
            throw new BadRequestAlertException("A new solucion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return solucionService
            .create(solucionDto)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/soluciones/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /solucions/:id} : Updates an existing solucion.
     *
     * @param id          the id of the solucionDto to save.
     * @param solucionDto the solucionDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated solucionDto,
     *         or with status {@code 400 (Bad Request)} if the solucionDto is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the solucionDto
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/soluciones/{id}")
    public Mono<ResponseEntity<SolucionDto>> updateSolucion(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SolucionDto solucionDto
    ) throws URISyntaxException {
        log.debug("REST request to update Solucion : {}, {}", id, solucionDto);
        if (solucionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solucionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solucionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return solucionService
                    .update(solucionDto)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                            .body(result)
                    );
            });
    }

    @PutMapping("/soluciones/{id}/publicaciones")
    public Mono<ResponseEntity<SolucionDto>> publicarSolucion(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SolucionDto solucionDto
    ) throws URISyntaxException {
        log.debug("REST request to update Solucion : {}, {}", id, solucionDto);
        if (solucionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solucionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solucionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return solucionService
                    .publicar(solucionDto)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                            .body(result)
                    );
            });
    }

    @PutMapping("/soluciones/{id}/archivar")
    public Mono<ResponseEntity<SolucionDto>> archivarSolucion(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SolucionDto solucionDto
    ) throws URISyntaxException {
        log.debug("REST request to update Solucion : {}, {}", id, solucionDto);
        if (solucionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solucionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solucionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return solucionService
                    .archivar(solucionDto)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /solucions/:id} : Partial updates given fields of an existing
     * solucion, field will ignore if it is null
     *
     * @param id          the id of the solucionDto to save.
     * @param solucionDto the solucionDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated solucionDto,
     *         or with status {@code 400 (Bad Request)} if the solucionDto is not
     *         valid,
     *         or with status {@code 404 (Not Found)} if the solucionDto is not
     *         found,
     *         or with status {@code 500 (Internal Server Error)} if the solucionDto
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/soluciones/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SolucionDto>> partialUpdateSolucion(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SolucionDto solucionDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Solucion partially : {}, {}", id, solucionDto);
        if (solucionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solucionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solucionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SolucionDto> result = solucionService.partialUpdate(solucionDto);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /solucions} : get all the solucions.
     *
     * @param pageable the pagination information.
     * @param request  a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of solucions in body.
     */
    @GetMapping("/soluciones")
    public Mono<ResponseEntity<List<SolucionDto>>> getAllSolucions(
        Filtro filtro,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Solucions");
        return solucionService
            .countAll(filtro)
            .zipWith(solucionService.findAll(filtro, pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            UriComponentsBuilder.fromHttpRequest(request),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    // TODO Utilizar getAllSolucions con filtro
    @GetMapping("/soluciones/activas")
    public Mono<ResponseEntity<List<SolucionDto>>> getAllSolucionesActivas(
        Filtro filtro,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Solucions");
        filtro.setEstadoSolucion(EstadoSolucion.PUBLICADA);
        filtro.setSolucionVisible(Boolean.TRUE);
        return solucionService
            .countAll(filtro)
            .zipWith(solucionService.findAll(filtro, pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            UriComponentsBuilder.fromHttpRequest(request),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /solucions/:id} : get the "id" solucion.
     *
     * @param id the id of the solucionDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the solucionDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/soluciones/{id}")
    public Mono<ResponseEntity<SolucionDto>> getSolucion(@PathVariable String id) {
        log.debug("REST request to get Solucion : {}", id);
        Mono<SolucionDto> solucionDto = solucionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(solucionDto);
    }

    @GetMapping("/soluciones/{id}/historicos")
    public Mono<ResponseEntity<SolucionDto>> getSolucionByLastEdited(@PathVariable String id) {
        log.debug("REST request to get last version of the Solucion : {}", id);
        Mono<SolucionDto> solucionDto = solucionService.findOneByLastVersion(id);
        return ResponseUtil.wrapOrNotFound(solucionDto);
    }

    /**
     * {@code DELETE  /solucions/:id} : delete the "id" solucion.
     *
     * @param id the id of the solucionDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/soluciones/{id}")
    public Mono<ResponseEntity<Void>> deleteSolucion(@PathVariable String id) {
        log.debug("REST request to delete Solucion : {}", id);
        return solucionService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
                )
            );
    }

    @GetMapping("/soluciones/{id}/evaluaciones")
    public Mono<ResponseEntity<DefinicionEvaluacionDto>> getEvaluaciones(@PathVariable String id) {
        log.debug("REST request to get revision definitions for solucion: {}", id);
        Mono<DefinicionEvaluacionDto> solucionDto = solucionService.findOneDefinicionEvaluacion(id);
        return ResponseUtil.wrapOrNotFound(solucionDto);
    }

    @PutMapping("/soluciones/{id}/evaluaciones")
    public Mono<ResponseEntity<DefinicionEvaluacionDto>> updateDefinicionEvaluacion(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DefinicionEvaluacionDto definicionEvaluacionDto
    ) throws URISyntaxException {
        log.debug("REST request to update/create DefinicionEvaluacion : {}, {}", id, definicionEvaluacionDto);

        if (!Objects.equals(id, definicionEvaluacionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solucionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    log.debug("Create DefinicionEvaluacion for solucionid: {}", id);
                }

                return solucionService
                    .saveDefinicionEvaluacion(definicionEvaluacionDto)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                            .body(result)
                    );
            });
    }
}
