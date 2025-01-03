package org.apeiron.kernel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.apeiron.kernel.repository.FormRepository;
import org.apeiron.kernel.service.FormService;
import org.apeiron.kernel.service.dto.FormDto;
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
 * REST controller for managing {@link org.apeiron.kernel.domain.Form}.
 */
@RestController
@RequestMapping("/api")
public class FormResource {

    private final Logger log = LoggerFactory.getLogger(FormResource.class);

    private static final String ENTITY_NAME = "form";

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    private final FormService formService;

    private final FormRepository formRepository;

    public FormResource(FormService formService, FormRepository formRepository) {
        this.formService = formService;
        this.formRepository = formRepository;
    }

    /**
     * {@code POST  /forms} : Create a new form.
     *
     * @param formDto the formDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new formDto, or with status {@code 400 (Bad Request)} if the
     *         form has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/forms")
    public Mono<ResponseEntity<FormDto>> createForm(@RequestBody FormDto formDto) throws URISyntaxException {
        log.debug("REST request to save Form : {}", formDto);
        return formService
            .save(formDto)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/forms/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /forms/:id} : Updates an existing form.
     *
     * @param id      the id of the formDto to save.
     * @param formDto the formDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated formDto,
     *         or with status {@code 400 (Bad Request)} if the formDto is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the formDto
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/forms/{id}")
    public Mono<ResponseEntity<FormDto>> updateForm(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FormDto formDto
    ) throws URISyntaxException {
        log.debug("REST request to update Form : {}, {}", id, formDto);
        if (formDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return formRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return formService
                    .update(formDto)
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
     * {@code PATCH  /forms/:id} : Partial updates given fields of an existing form,
     * field will ignore if it is null
     *
     * @param id      the id of the formDto to save.
     * @param formDto the formDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated formDto,
     *         or with status {@code 400 (Bad Request)} if the formDto is not valid,
     *         or with status {@code 404 (Not Found)} if the formDto is not found,
     *         or with status {@code 500 (Internal Server Error)} if the formDto
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/forms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FormDto>> partialUpdateForm(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FormDto formDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Form partially : {}, {}", id, formDto);
        if (formDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return formRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FormDto> result = formService.partialUpdate(formDto);

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
     * {@code GET  /forms} : get all the forms.
     *
     * @param pageable the pagination information.
     * @param request  a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of forms in body.
     */
    @GetMapping("/forms")
    public Mono<ResponseEntity<List<FormDto>>> getAllForms(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Forms");
        return formService
            .countAll()
            .zipWith(formService.findAll(pageable).collectList())
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
     * {@code GET  /forms/:id} : get the "id" form.
     *
     * @param id the id of the formDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the formDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/forms/{id}")
    public Mono<ResponseEntity<FormDto>> getForm(@PathVariable String id) {
        log.debug("REST request to get Form : {}", id);
        Mono<FormDto> formDto = formService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formDto);
    }

    /**
     * {@code DELETE  /forms/:id} : delete the "id" form.
     *
     * @param id the id of the formDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/forms/{id}")
    public Mono<ResponseEntity<Void>> deleteForm(@PathVariable String id) {
        log.debug("REST request to delete Form : {}", id);
        return formService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
                )
            );
    }
}
