package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.HistoricoSolucion;
import org.apeiron.kernel.domain.HistoricoSolucionId;
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
public interface HistoricoSolucionRepository
    extends ReactiveMongoRepository<HistoricoSolucion, HistoricoSolucionId>, ReactiveQuerydslPredicateExecutor<HistoricoSolucion> {
    Flux<HistoricoSolucion> findAllBy(Pageable pageable);
}
