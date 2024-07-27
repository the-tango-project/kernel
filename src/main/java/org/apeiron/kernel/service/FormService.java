package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.FormDto;
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
     * @param formDto the entity to save.
     * @return the persisted entity.
     */
    Mono<FormDto> save(FormDto formDto);

    /**
     * Updates a form.
     *
     * @param formDto the entity to update.
     * @return the persisted entity.
     */
    Mono<FormDto> update(FormDto formDto);

    /**
     * Partially updates a form.
     *
     * @param formDto the entity to update partially.
     * @return the persisted entity.
     */
    Mono<FormDto> partialUpdate(FormDto formDto);

    /**
     * Get all the forms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<FormDto> findAll(Pageable pageable);

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
    Mono<FormDto> findOne(String id);

    /**
     * Delete the "id" form.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
