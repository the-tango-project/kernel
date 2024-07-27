package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.DocumentoDto;
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
     * @param documentoDto the entity to save.
     * @return the persisted entity.
     */
    Mono<DocumentoDto> save(DocumentoDto documentoDto);

    /**
     * Updates a documento.
     *
     * @param documentoDto the entity to update.
     * @return the persisted entity.
     */
    Mono<DocumentoDto> update(DocumentoDto documentoDto);

    /**
     * Partially updates a documento.
     *
     * @param documentoDto the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DocumentoDto> partialUpdate(DocumentoDto documentoDto);

    /**
     * Get all the documentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DocumentoDto> findAll(Pageable pageable);

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
    Mono<DocumentoDto> findOne(String id);

    /**
     * Delete the "id" documento.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
