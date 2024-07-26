package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Documento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the Documento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentoRepository extends ReactiveMongoRepository<Documento, String>, ReactiveQuerydslPredicateExecutor<Documento> {
    Flux<Documento> findAllBy(Pageable pageable);
}
