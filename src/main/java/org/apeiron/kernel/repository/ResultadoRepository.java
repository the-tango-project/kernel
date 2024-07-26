package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Resultado;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends ReactiveMongoRepository<Resultado, String>, ReactiveQuerydslPredicateExecutor<Resultado> {}
