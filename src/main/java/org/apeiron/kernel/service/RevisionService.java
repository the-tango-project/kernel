package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.RevisionDto;
import org.apeiron.kernel.service.util.FiltroRevision;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing
 * {@link org.apeiron.kernel.domain.Revision}.
 */
public interface RevisionService {
    /**
     * Save a revision.
     *
     * @param revisionDto the entity to save.
     * @return the persisted entity.
     */
    Mono<RevisionDto> save(RevisionDto revisionDto);

    /**
     * Save a revision.
     *
     * @param revisionDto the entity to save.
     * @return the persisted entity.
     */
    Mono<RevisionDto> confirmarRevision(RevisionDto revisionDto);

    /**
     * Updates a revision.
     *
     * @param revisionDto the entity to update.
     * @return the persisted entity.
     */
    Mono<RevisionDto> update(RevisionDto revisionDto);

    /**
     * Partially updates a revision.
     *
     * @param revisionDto the entity to update partially.
     * @return the persisted entity.
     */
    Mono<RevisionDto> partialUpdate(RevisionDto revisionDto);

    /**
     * Get all the revisions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<RevisionDto> findAll(Pageable pageable);

    /**
     * Get all the revisions by filtro
     *
     * @param filtro   the filtro of the request
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<RevisionDto> findAll(FiltroRevision filtro, Pageable pageable);

    /**
     * Returns the number of revisions available.
     *
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Returns the number of revisions available by filtro
     *
     * @param filtro of the request
     * @return the number of entities in the database by filtro
     *
     */
    Mono<Long> countAll(FiltroRevision filtro);

    /**
     * Get the "id" revision.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<RevisionDto> findOne(String id);

    /**
     * Delete the "id" revision.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);

    /**
     * Get the "RevisionDto" revision.
     *
     * @param solicitudId the solicitudId
     * @param revisorId and revisorId and evaluacionId the
     *                    solicitudResumen.solicitudId and
     *                    revisor.revisorId and evaluacionId of the revisionDto to
     *                    retrieve.
     * @param evaluacionId the evaluacionId
     * @return the entity.
     */
    public Mono<RevisionDto> findBysolicitudIdAndRevisorId(String solicitudId, String revisorId, String evaluacionId);

    /**
     * Get the "RevisionDto" revision.
     *
     * @param solicitudId the solicitudId
     * @param evaluacionId and revisorId and evaluacionId the
     *                    solicitudResumen.solicitudId and
     *                    revisor.revisorId and evaluacionId of the revisionDto to
     *                    retrieve.
     * @return the entity.
     */
    public Flux<RevisionDto> findAllRevisionesBySolicitudId(String solicitudId, String evaluacionId);

    /**
     * Get the "RevisionDto" revision.
     *
     * @param filtro the filter to find a revisor
     * @return the revisorDto entity
     */
    Mono<RevisionDto> findByEvaluacionIdAndRevisorId(FiltroRevision filtro);
}
