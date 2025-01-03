package org.apeiron.kernel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.apeiron.kernel.domain.enumeration.EstadoRevision;
import org.apeiron.kernel.repository.RevisionRepository;
import org.apeiron.kernel.service.RevisionService;
import org.apeiron.kernel.service.dto.RevisionDto;
import org.apeiron.kernel.service.util.FiltroRevision;
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
 * {@link org.apeiron.kernel.domain.Revision}.
 */
@RestController
@RequestMapping("/api")
public class RevisionResource {

    private final Logger log = LoggerFactory.getLogger(RevisionResource.class);

    private static final String ENTITY_NAME = "revision";

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    private final RevisionService revisionService;

    private final RevisionRepository revisionRepository;

    public RevisionResource(
            RevisionService revisionService,
            RevisionRepository revisionRepository) {
        this.revisionService = revisionService;
        this.revisionRepository = revisionRepository;
    }

    /**
     * {@code POST  /revisiones} : Create a new revision.
     *
     * @param revisionDto the revisionDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new revisionDto, or with status {@code 400 (Bad Request)} if
     *         the revision has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/revisiones")
    public Mono<ResponseEntity<RevisionDto>> createRevision(@RequestBody RevisionDto revisionDto)
            throws URISyntaxException {
        log.debug("REST request to save Revision : {}", revisionDto);
        if (revisionDto.getId() != null) {
            throw new BadRequestAlertException("A new revision cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return revisionService
                .confirmarRevision(revisionDto)
                .map(result -> {
                    try {
                        return ResponseEntity
                                .created(new URI("/api/revisiones/" + result.getId()))
                                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                                        result.getId()))
                                .body(result);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * {@code PUT  /revisiones/:id} : Updates an existing revision.
     *
     * @param id          the id of the revisionDto to save.
     * @param revisionDto the revisionDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated revisionDto,
     *         or with status {@code 400 (Bad Request)} if the revisionDto is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the revisionDto
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/revisiones/{id}")
    public Mono<ResponseEntity<RevisionDto>> updateRevision(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody RevisionDto revisionDto) throws URISyntaxException {
        log.debug("REST request to update Revision : {}, {}", id, revisionDto);
        if (revisionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, revisionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return revisionRepository
                .existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                    }

                    return revisionService
                            .update(revisionDto)
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                            .map(result -> ResponseEntity
                                    .ok()
                                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                            result.getId()))
                                    .body(result));
                });
    }

    @PutMapping("/revisiones/enviar/{id}")
    public Mono<ResponseEntity<RevisionDto>> sendRevision(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody RevisionDto revisionDto) throws URISyntaxException {
        log.debug("REST request to update Revision : {}, {}", id, revisionDto);
        if (revisionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, revisionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        revisionDto.setEstado(EstadoRevision.REVISADA);
        return revisionRepository
                .existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                    }

                    return revisionService
                            .update(revisionDto)
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                            .map(result -> ResponseEntity
                                    .ok()
                                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                            result.getId()))
                                    .body(result));
                });
    }

    /**
     * {@code PATCH  /revisiones/:id} : Partial updates given fields of an existing
     * revision, field will ignore if it is null
     *
     * @param id          the id of the revisionDto to save.
     * @param revisionDto the revisionDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated revisionDto,
     *         or with status {@code 400 (Bad Request)} if the revisionDto is not
     *         valid,
     *         or with status {@code 404 (Not Found)} if the revisionDto is not
     *         found,
     *         or with status {@code 500 (Internal Server Error)} if the revisionDto
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/revisiones/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<RevisionDto>> partialUpdateRevision(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody RevisionDto revisionDto) throws URISyntaxException {
        log.debug("REST request to partial update Revision partially : {}, {}", id, revisionDto);
        if (revisionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, revisionDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return revisionRepository
                .existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                    }

                    Mono<RevisionDto> result = revisionService.partialUpdate(revisionDto);

                    return result
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                            .map(res -> ResponseEntity
                                    .ok()
                                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                            res.getId()))
                                    .body(res));
                });
    }

    /**
     * {@code GET  /revisiones} : get all the revisiones.
     *
     * @param pageable the pagination information.
     * @param request  a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of revisiones in body.
     */
    @GetMapping("/revisiones")
    public Mono<ResponseEntity<List<RevisionDto>>> getAllRevisions(
            FiltroRevision filtro,
            @org.springdoc.core.annotations.ParameterObject Pageable pageable,
            ServerHttpRequest request) {
        log.debug("REST request to get a page of Revisions");
        return revisionService
                .countAll(filtro)
                .zipWith(revisionService.findAll(filtro, pageable).collectList())
                .map(countWithEntities -> ResponseEntity
                        .ok()
                        .headers(
                                PaginationUtil.generatePaginationHttpHeaders(
                                        UriComponentsBuilder.fromHttpRequest(request),
                                        new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())))
                        .body(countWithEntities.getT2()));
    }

    /**
     * {@code GET  /revisiones/:id} : get the "id" revision.
     *
     * @param id the id of the revisionDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the revisionDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/revisiones/{id}")
    public Mono<ResponseEntity<RevisionDto>> getRevision(@PathVariable String id) {
        log.debug("REST request to get Revision : {}", id);
        Mono<RevisionDto> revisionDto = revisionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(revisionDto);
    }

    /**
     * {@code GET  /revisiones/:evaluacionId/:revisorId} : get the "evaluacionId"
     * and "revisorId" revision.
     *
     * @param filtro the filtro to retrieve information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the revisionDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/revisiones/realizadas")
    public Mono<ResponseEntity<RevisionDto>> getRevisionByFiltro(FiltroRevision filtro) {
        log.debug("REST request to get Revision : {}", filtro);
        Mono<RevisionDto> revisionDto = revisionService.findByEvaluacionIdAndRevisorId(filtro);
        return ResponseUtil.wrapOrNotFound(revisionDto);
    }

    /**
     * {@code DELETE  /revisiones/:id} : delete the "id" revision.
     *
     * @param id the id of the revisionDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/revisiones/{id}")
    public Mono<ResponseEntity<Void>> deleteRevision(@PathVariable String id) {
        log.debug("REST request to delete Revision : {}", id);
        return revisionService
                .delete(id)
                .then(
                        Mono.just(
                                ResponseEntity.noContent().headers(
                                        HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id))
                                        .build()));
    }

    /**
     * {@code GET /revisiones/obtener-por-solicitud-revisor/:solicitudId} : get the
     * "id" revision.
     *
     * @param solicitudId  and revisorId and evaluacionId the
     *                     solicitudResumen.solicitudId and
     *                     revisor.revisorId and evaluacionIdof the revisionDto to
     *                     retrieve
     * @param revisorId
     * @param evaluacionId
     *
     * @return the response entity
     * 
     */
    @GetMapping("/revisiones/obtener-por-solicitud-revisor/{solicitudId}")
    public Mono<ResponseEntity<RevisionDto>> getRevisionBySolicitudIdAndRevisorId(
            @PathVariable String solicitudId,
            String revisorId,
            String evaluacionId) {
        log.debug("REST request to get Revision : {}", solicitudId);
        Mono<RevisionDto> revisionDto = revisionService.findBysolicitudIdAndRevisorId(solicitudId, revisorId,
                evaluacionId);
        return ResponseUtil.wrapOrNotFound(revisionDto);
    }

    /**
     * {@code GET  /revisiones} : get all the revisiones.
     *
     * @param solicitudId  and revisorId and evaluacionId the
     *                     solicitudResumen.solicitudId and
     *                     revisor.revisorId and evaluacionIdof the revisionDto
     * @param evaluacionId the evaluacionId to search
     * @param request      a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of revisiones in body.
     */
    @GetMapping("/revisiones/obtener-por-solicitud/{solicitudId}")
    public Mono<ResponseEntity<List<RevisionDto>>> getAllRevisionesBySolicitudId(
            @PathVariable String solicitudId,
            String evaluacionId,
            ServerHttpRequest request) {
        log.debug("REST request to get a page of Revisions");
        return revisionService
                .findAllRevisionesBySolicitudId(solicitudId, evaluacionId)
                .collectList()
                .map(revisionesDto -> ResponseEntity.ok().body(revisionesDto));
    }

}
