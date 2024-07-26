package org.apeiron.kernel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.apeiron.kernel.repository.DocumentoRepository;
import org.apeiron.kernel.service.DocumentoService;
import org.apeiron.kernel.service.dto.DocumentoDTO;
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
 * REST controller for managing {@link mx.conacyt.domain.Documento}.
 */
@RestController
@RequestMapping("/api")
public class DocumentoResource {

    private final Logger log = LoggerFactory.getLogger(DocumentoResource.class);

    private static final String ENTITY_NAME = "documento";

    @Value("${jhipster.clientApp.name}")
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
     * @param documentoDTO the documentoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new documentoDTO, or with status {@code 400 (Bad Request)}
     *         if the documento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/documentos")
    public Mono<ResponseEntity<DocumentoDTO>> createDocumento(@RequestBody DocumentoDTO documentoDTO) throws URISyntaxException {
        log.debug("REST request to save Documento : {}", documentoDTO);
        return documentoService
            .save(documentoDTO)
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
     * @param id           the id of the documentoDTO to save.
     * @param documentoDTO the documentoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated documentoDTO,
     *         or with status {@code 400 (Bad Request)} if the documentoDTO is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         documentoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/documentos/{id}")
    public Mono<ResponseEntity<DocumentoDTO>> updateDocumento(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DocumentoDTO documentoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Documento : {}, {}", id, documentoDTO);
        if (documentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return documentoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return documentoService
                    .update(documentoDTO)
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
    public Mono<ResponseEntity<DocumentoDTO>> getDocumento(@PathVariable String id) {
        log.debug("REST request to get Documento : {}", id);
        Mono<DocumentoDTO> documentoDTO = documentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentoDTO);
    }
}
