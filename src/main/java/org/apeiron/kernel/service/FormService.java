package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.FormDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link org.apeiron.kernel.domain.Form}.
 */
public interface FormService {
    /**
     * Save a form.
     *
     * @param formDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<FormDTO> save(FormDTO formDTO);

    /**
     * Updates a form.
     *
     * @param formDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<FormDTO> update(FormDTO formDTO);

    /**
     * Partially updates a form.
     *
     * @param formDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<FormDTO> partialUpdate(FormDTO formDTO);

    /**
     * Get all the forms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<FormDTO> findAll(Pageable pageable);

    /**
     * Returns the number of forms available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" form.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<FormDTO> findOne(String id);

    /**
     * Delete the "id" form.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
