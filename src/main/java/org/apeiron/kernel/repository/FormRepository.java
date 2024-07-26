package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Form;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the Form entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormRepository extends ReactiveMongoRepository<Form, String> {
    Flux<Form> findAllBy(Pageable pageable);
}
