package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.DefinicionEvaluacion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB reactive repository for the Solucion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DefinicionEvaluacionRepository
    extends ReactiveMongoRepository<DefinicionEvaluacion, String>, ReactiveQuerydslPredicateExecutor<DefinicionEvaluacion> {}
