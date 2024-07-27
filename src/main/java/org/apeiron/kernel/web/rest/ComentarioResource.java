package org.apeiron.kernel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import jakarta.validation.Valid;
import org.apeiron.kernel.repository.ComentarioRepository;
import org.apeiron.kernel.service.ComentarioService;
import org.apeiron.kernel.service.dto.ComentarioDto;
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
import org.springframework.web.bind.annotation.GetMapping;
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

/**
 * REST controller for managing {@link org.apeiron.kernel.domain.Comentario}.
 */
@RestController
@RequestMapping("/api")
public class ComentarioResource {

    private final Logger log = LoggerFactory.getLogger(ComentarioResource.class);

    private static final String ENTITY_NAME = "comentario";

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    private final ComentarioService comentarioService;

    private final ComentarioRepository comentarioRepository;

    public ComentarioResource(ComentarioService comentarioService, ComentarioRepository comentarioRepository) {
        this.comentarioService = comentarioService;
        this.comentarioRepository = comentarioRepository;
    }

    /**
     * {@code POST  /comentarios} : Create a new comentario.
     *
     * @param comentarioDto the comentarioDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comentarioDto, or with status {@code 400 (Bad Request)} if the comentario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comentarios")
    public Mono<ResponseEntity<ComentarioDto>> createComentario(@Valid @RequestBody ComentarioDto comentarioDto) throws URISyntaxException {
        log.debug("REST request to save Comentario : {}", comentarioDto);
        if (comentarioDto.getId() != null) {
            throw new BadRequestAlertException("A new comentario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return comentarioService
            .save(comentarioDto)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/comentarios/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /comentarios/:id} : Updates an existing comentario.
     *
     * @param id the id of the comentarioDto to save.
     * @param comentarioDto the comentarioDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comentarioDto,
     * or with status {@code 400 (Bad Request)} if the comentarioDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comentarioDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comentarios/{id}")
    public Mono<ResponseEntity<ComentarioDto>> updateComentario(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody ComentarioDto comentarioDto
    ) throws URISyntaxException {
        log.debug("REST request to update Comentario : {}, {}", id, comentarioDto);
        if (comentarioDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, comentarioDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return comentarioRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return comentarioService
                    .update(comentarioDto)
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
     * {@code GET  /comentarios} : get all the comentarios.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comentarios in body.
     */
    @GetMapping("/comentarios")
    public Mono<ResponseEntity<List<ComentarioDto>>> getAllComentarios(Filtro filtro, Pageable pageable, ServerHttpRequest request) {
        log.debug("REST request to get a page of Comentarios");
        return comentarioService
            .countAll(filtro)
            .zipWith(comentarioService.findAll(filtro, pageable).collectList())
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
}
