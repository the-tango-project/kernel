package org.apeiron.kernel.repository;

import java.time.Instant;
import java.util.List;
import org.apeiron.kernel.domain.Reporte;
import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data MongoDB reactive repository for the Solicitud entity.
 * FIXME: Se tiene que reestructurar de tal manera que sólo se tengan consultas
 * genéricas para buscar solicitudes
 */
@SuppressWarnings("unused")
@Repository
public interface SolicitudRepository
        extends ReactiveMongoRepository<Solicitud, String>, ReactiveQuerydslPredicateExecutor<Solicitud> {
    @Query(value = "{ 'id' : ?0 }", fields = "{ 'revisores' : 1}")
    Mono<Solicitud> findRevisoresById(String id);

    Mono<Solicitud> findBySolucionId(String solucionId);

    Mono<Solicitud> findByUsuarioAndSolucionId(String usuario, String solucionId);

    Mono<Solicitud> findFirstByUsuarioAndSolucionId(String usuario, String solucionId);

    Mono<Solicitud> findByUsuarioAndSolucionIdAndEstado(String usuario, String solucionId, EstadoSolicitud estado);

    Flux<Solicitud> findAllBy(Pageable pageable);

    Flux<Solicitud> findAllByUsuario(String usuario, Pageable pageable);

    @Query(value = "{ revisores: { $elemMatch: { revisor_id: ?0, estado: 'ASIGNADA' }  } }", fields = "{ 'nombre' : 1, 'revisores' : 1 }")
    Flux<Solicitud> findAllByRevisoresIn(String revisorId);

    Flux<Solicitud> findAllByUsuarioAndSolucionId(String usuario, String solucionId, Pageable pageable);

    @Query(value = "{   usuario: ?0, solucionId: ?1,    $or: [{estado: 'ENVIADA'}, {estado: 'APROBADA'}, {estado: 'RECHAZADA'} ] } ", fields = "{ 'nombre' : 1 }")
    Flux<Solicitud> getSolicitudesInProceso(String usuarioId, String solucionId);

    @Query(value = "{   usuario: ?0, solucionId: {$in : ?1} }", fields = "{ 'nombre' : 1 , 'estado' : 1}")
    Flux<Solicitud> findAllByAllSoluciones(String usuario, List<String> solucionIds);

    @Query("{'solucionId': ?0, 'estado': ?1 , 'properties.estatus_informe_mensual': ?2}")
    Flux<Solicitud> findBySolucionIdAndEstadoAndEstatusInforme(String solucionId, EstadoSolicitud estado,
            Boolean estadoInforme);

    @Aggregation(pipeline = { "{ '$match': { 'solucionId': ?0 } }",
            "{ '$group': { '_id': '$estado', 'count': { '$sum': 1 } } }" })
    Flux<Reporte> countTotalSolicitudesByEstado(String solucionId);

    @Query(value = "{ 'solicitante.cvu': ?0, solucionId: {$in : ?1} }")
    Flux<Solicitud> findSolicitudByCVUSoluciones(String cvu, List<String> solucionIds);

    @Query(value = "{ 'solicitante.cvu': ?0 }", exists = true)
    Mono<Boolean> existsSolicitudesByCvu(String cvu);

    /**
     * Comprueba si existen solicitudes asociadas a la dependencia dada que no están
     * en el estado 'CANCELADA' o 'FORMALIZADA'.
     *
     * @param idInstitucion El ID de la institución.
     * @param idDependencia El ID de la dependencia.
     * @return Un Mono<Boolean> que indica si existen solicitudes para la
     *         dependencia especificada.
     */
    @Query(value = "{ 'properties.institucionActualSubSeccion.idInstitucion': ?0, 'properties.institucionActualSubSeccion.idDependencia': ?1, 'estado': { $nin: ['CANCELADA', 'FORMALIZADA'] } }", exists = true)
    Mono<Boolean> existeSolicitudesPorDependencia(Long idInstitucion, Long idDependencia);

    /**
     * Comprueba si existen solicitudes asociadas a la subdependencia dada que no
     * están en el estado 'CANCELADA' o 'FORMALIZADA'.
     *
     * @param idInstitucion    El ID de la institución.
     * @param idDependencia    El ID de la dependencia.
     * @param idSubDependencia El ID de la subdependencia.
     * @return Un Mono<Boolean> que indica si existen solicitudes para la
     *         subdependencia especificada.
     */
    @Query(value = "{ 'properties.institucionActualSubSeccion.idInstitucion': ?0, 'properties.institucionActualSubSeccion.idDependencia': ?1, 'properties.institucionActualSubSeccion.idSubDependencia': ?2, 'estado': { $nin: ['CANCELADA', 'FORMALIZADA'] } }", exists = true)
    Mono<Boolean> existeSolicitudesPorSubDependencia(Long idInstitucion, Long idDependencia, Long idSubDependencia);

    /**
     * Comprueba si existen solicitudes asociadas al departamento dado que no están
     * en el estado 'CANCELADA' o 'FORMALIZADA'.
     *
     * @param idInstitucion    El ID de la institución.
     * @param idDependencia    El ID de la dependencia.
     * @param idSubDependencia El ID de la subdependencia.
     * @param idDepartamento   El ID del departamento.
     * @return Un Mono<Boolean> que indica si existen solicitudes para el
     *         departamento especificado.
     */
    @Query(value = "{ 'properties.institucionActualSubSeccion.idInstitucion': ?0, 'properties.institucionActualSubSeccion.idDependencia': ?1, 'properties.institucionActualSubSeccion.idSubDependencia': ?2, 'properties.institucionActualSubSeccion.idDepartamento': ?3, 'estado': { $nin: ['CANCELADA', 'FORMALIZADA'] } }", exists = true)
    Mono<Boolean> existeSolicitudesPorDepartamento(Long idInstitucion, Long idDependencia, Long idSubDependencia,
            Long idDepartamento);

    /**
     * Busca todas la solicitudes pendientes de enviar a expedientes, que sean de
     * tipo Reclasificacion de ayudantes y que sean menor o igual a la fecha
     * 
     * @param date Filtro fecha
     * @return Solicitudes pendientes de enviar a expedientes
     */
    @Aggregation(pipeline = {
            "{ $match: { $and: [ {'properties.expediente': false }, {'properties.tipoMovimiento': 'Reclasificación ayudantes'} ] } }",
            "{ $addFields: { fechaEfectivaReclasificacion: { '$convert': { 'input': '$properties.fechaEfectivaReclasificacion', 'to': 'date' } } } }",
            "{ $match: { fechaEfectivaReclasificacion: { $lte: ?0 } } }",
    })
    Flux<Solicitud> findAllSolicitudesReclasificadasPendienteEnviarExp(Instant date);

    /**
     * Busca todas la solicitudes de ayudantias pendientes de terminar por solucion
     * id, estado formalizada y no vigentes
     * 
     * @param solucionId Id de la solución
     * @param date       Filtro fecha
     * @return Solicitudes pendientes de cerrar
     */
    @Aggregation(pipeline = {
            "{ $match: { $and: [ {'solucionId': ?0 }, {'estado': 'FORMALIZADA'} ] } }",
            "{ $addFields: { fecha_fin: { '$convert': { 'input': '$properties.fecha_fin', 'to': 'date' } } } }",
            "{ $match: { fecha_fin: { $lt: ?1 } } }",
    })
    Flux<Solicitud> findAllSolicitudesNoVigentes(String solucionId, Instant date);
}
