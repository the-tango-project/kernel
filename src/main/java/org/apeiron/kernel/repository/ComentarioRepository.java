package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Comentario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the Comentario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComentarioRepository extends ReactiveMongoRepository<Comentario, String>, ReactiveQuerydslPredicateExecutor<Comentario> {
    Flux<Comentario> findAllBy(Pageable pageable);
}
