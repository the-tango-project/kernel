package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.RevisionDTO;
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
     * @param revisionDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<RevisionDTO> save(RevisionDTO revisionDTO);

    /**
     * Save a revision.
     *
     * @param revisionDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<RevisionDTO> confirmarRevision(RevisionDTO revisionDTO);

    /**
     * Updates a revision.
     *
     * @param revisionDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<RevisionDTO> update(RevisionDTO revisionDTO);

    /**
     * Partially updates a revision.
     *
     * @param revisionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<RevisionDTO> partialUpdate(RevisionDTO revisionDTO);

    /**
     * Get all the revisions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<RevisionDTO> findAll(Pageable pageable);

    /**
     * Get all the revisions by filtro
     *
     * @param filtro   the filtro of the request
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<RevisionDTO> findAll(FiltroRevision filtro, Pageable pageable);

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
    Mono<RevisionDTO> findOne(String id);

    /**
     * Delete the "id" revision.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);

    /**
     * Get the "RevisionDTO" revision.
     *
     * @param idSolicitud and revisorId and evaluacionId the
     *                    solicitudResumen.solicitudId and
     *                    revisor.revisorId and evaluacionId of the revisionDTO to
     *                    retrieve.
     * @return the entity.
     */
    public Mono<RevisionDTO> findBysolicitudIdAndRevisorId(String solicitudId, String revisorId, String evaluacionId);

    /**
     * Get the "RevisionDTO" revision.
     *
     * @param idSolicitud and revisorId and evaluacionId the
     *                    solicitudResumen.solicitudId and
     *                    revisor.revisorId and evaluacionId of the revisionDTO to
     *                    retrieve.
     * @return the entity.
     */
    public Flux<RevisionDTO> findAllRevisionesBySolicitudId(String solicitudId, String evaluacionId);

    /**
     * Get the "RevisionDTO" revision.
     *
     * @param evaluacionId and revisorId the solicitudResumen.solicitudId and
     *                     revisor.revisorId of the revisionDTO to retrieve.
     * @param revisorId    and revisorId the solicitudResumen.solicitudId and
     *                     revisor.revisorId of the revisionDTO to retrieve.
     * @return the entity.
     */
    Mono<RevisionDTO> findByEvaluacionIdAndRevisorId(FiltroRevision filtro);
}
