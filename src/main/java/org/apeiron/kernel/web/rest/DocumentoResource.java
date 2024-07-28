package org.apeiron.kernel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.apeiron.kernel.repository.DocumentoRepository;
import org.apeiron.kernel.service.DocumentoService;
import org.apeiron.kernel.service.dto.DocumentoDto;
import org.apeiron.kernel.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link org.apeiron.kernel.domain.Documento}.
 */
@RestController
@RequestMapping("/api")
public class DocumentoResource {

    private final Logger log = LoggerFactory.getLogger(DocumentoResource.class);

    private static final String ENTITY_NAME = "documento";

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    private final DocumentoService documentoService;

    private final DocumentoRepository documentoRepository;

    public DocumentoResource(DocumentoService documentoService, DocumentoRepository documentoRepository) {
        this.documentoService = documentoService;
        this.documentoRepository = documentoRepository;
    }

    /**
     * {@code POST  /documentos} : Create a new documento.
     *
     * @param documentoDto the documentoDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new documentoDto, or with status {@code 400 (Bad Request)}
     *         if the documento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/documentos")
    public Mono<ResponseEntity<DocumentoDto>> createDocumento(@RequestBody DocumentoDto documentoDto) throws URISyntaxException {
        log.debug("REST request to save Documento : {}", documentoDto);
        return documentoService
            .save(documentoDto)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/documentos/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /documentos/:id} : Updates an existing documento.
     *
     * @param id           the id of the documentoDto to save.
     * @param documentoDto the documentoDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated documentoDto,
     *         or with status {@code 400 (Bad Request)} if the documentoDto is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         documentoDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/documentos/{id}")
    public Mono<ResponseEntity<DocumentoDto>> updateDocumento(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DocumentoDto documentoDto
    ) throws URISyntaxException {
        log.debug("REST request to update Documento : {}, {}", id, documentoDto);
        if (documentoDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentoDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return documentoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return documentoService
                    .update(documentoDto)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                            .body(result)
                    );
            });
    }

    @GetMapping("/documentos/{id}")
    public Mono<ResponseEntity<DocumentoDto>> getDocumento(@PathVariable String id) {
        log.debug("REST request to get Documento : {}", id);
        Mono<DocumentoDto> documentoDto = documentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentoDto);
    }
}
