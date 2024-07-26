package org.apeiron.kernel.repository;

import org.apeiron.kernel.domain.Reporte;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReporteRepository extends ReactiveMongoRepository<Reporte, String>, ReactiveQuerydslPredicateExecutor<Reporte> {
    @Aggregation(pipeline = { "{ '$match': { 'solucionId': ?0 } }", "{ '$group': { '_id': '$estado', 'count': { '$sum': 1 } } }" })
    Flux<Reporte> generarReporte(String solucionId);
}
