package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Revision;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data MongoDB reactive repository for the Revision entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RevisionRepository extends ReactiveMongoRepository<Revision, String>, ReactiveQuerydslPredicateExecutor<Revision> {
    Flux<Revision> findAllBy(Pageable pageable);

    /**
     * Get the "Revision" revision.
     *
     * @param revisorId the
     *                  revisor.revisorId of the revision to
     *                  retrieve.
     * @return the entity.
     */
    @Query("{ 'revisor.revisorId': ?0 }")
    Flux<Revision> findAllByRevisor(String revisorId, Pageable pageable);

    /**
     * Get the "Revision" revision.
     *
     * @param solicitudId and revisorId and evaluacionId the
     *                    solicitudResumen.solicitudId and
     *                    revisor.revisorId and evaluacionId of the revision to
     *                    retrieve.
     * @param revisorId the revisorId
     * @param evaluacionId the evaluacionId
     * 
     * @return the revision finded
     */
    @Query("{ 'solicitudResumen.solicitudId': ?0, 'revisor.revisorId': ?1, 'evaluacion_id': ?2 }")
    Mono<Revision> findBysolicitudIdAndRevisorId(String solicitudId, String revisorId, String evaluacionId);

    /**
     * Get the "Revision" revision.
     *
     * @param solicitudId and evaluacionId the
     *                    solicitudResumen.solicitudId and
     *                    evaluacionId of the revision to
     *                    retrieve.
     * @param evaluacionId the evaluacionId
     * @return the entity.
     */
    @Query("{ 'solicitudResumen.solicitudId': ?0, 'evaluacion_id': ?1 }")
    Flux<Revision> findAllRevisionesBySolicitudIdAndEvaluacionId(String solicitudId, String evaluacionId);

    /**
     * Get the "Revision" revision.
     *
     * @param revisorId and evaluacionId the
     *                  revisor.revisorId and evaluacionId of the revision to
     *                  retrieve.
     * @return the entity.
     */
    @Query("{ 'evaluacionId': ?0, 'revisor.revisorId': ?1 }")
    Mono<Revision> findByEvaluacionIdAndRevisorId(String evaluacionId, String revisorId);
}
