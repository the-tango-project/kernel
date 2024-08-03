package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Authority;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
@Repository
public interface AuthorityRepository extends ReactiveMongoRepository<Authority, String> {}
