package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Solucion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the Solucion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SolucionRepository extends ReactiveMongoRepository<Solucion, String>, ReactiveQuerydslPredicateExecutor<Solucion> {
    Flux<Solucion> findAllBy(Pageable pageable);

    Flux<Solucion> findAllByEstado(String estado, Pageable pageable);
}
