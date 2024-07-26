package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.DocumentoDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link mx.conacyt.domain.Documento}.
 */
public interface DocumentoService {
    /**
     * Save a documento.
     *
     * @param documentoDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DocumentoDTO> save(DocumentoDTO documentoDTO);

    /**
     * Updates a documento.
     *
     * @param documentoDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<DocumentoDTO> update(DocumentoDTO documentoDTO);

    /**
     * Partially updates a documento.
     *
     * @param documentoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DocumentoDTO> partialUpdate(DocumentoDTO documentoDTO);

    /**
     * Get all the documentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DocumentoDTO> findAll(Pageable pageable);

    /**
     * Returns the number of documentos available.
     *
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" documento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DocumentoDTO> findOne(String id);

    /**
     * Delete the "id" documento.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
