package org.apeiron.kernel.service;

import java.util.List;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.service.dto.SolicitudDto;
import org.apeiron.kernel.service.util.Filtro;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing
 * {@link org.apeiron.kernel.domain.Solicitud}.
 */
public interface SolicitudService {
    /**
     * Save a solicitud.
     *
     * @param solicitudDto the entity to save.
     * @return the persisted entity.
     */
    Mono<SolicitudDto> save(SolicitudDto solicitudDto);

    Mono<SolicitudDto> migrar(SolicitudDto solicitudDto);

    /**
     * Updates a solicitud.
     *
     * @param solicitudDto the entity to update.
     * @return the persisted entity.
     */
    Mono<SolicitudDto> update(SolicitudDto solicitudDto);

    /**
     * Updates a solicitud.
     *
     * @param solicitudDto the entity to update.
     * @return the persisted entity.
     */
    Mono<SolicitudDto> updateRevisores(SolicitudDto solicitudDto);

    /**
     * Partially updates a solicitud.
     *
     * @param solicitudDto the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SolicitudDto> partialUpdate(SolicitudDto solicitudDto);

    /**
     * Get all the solicituds.
     *
     * @param filtro the information filter.
     * @return the list of entities.
     */
    Flux<SolicitudDto> findAll(Filtro filtro);

    /**
     * Get all the solicituds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SolicitudDto> findAll(Filtro filtro, Pageable pageable);

    Flux<SolicitudDto> findAllInvitaciones();

    /**
     * Returns the number of solicituds available.
     *
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    Mono<Long> countAll(Filtro filtro);

    /**
     * Get the "id" solicitud.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SolicitudDto> findOne(String id);

    Mono<SolicitudDto> findRevisoresById(String id);

    Mono<SolicitudDto> findBySolucionId(String id);

    Mono<SolicitudDto> findBySolucionIdAndEstado(String id, EstadoSolicitud estado);

    /**
     * Consulta la lista de solicitudes asociadas a solucionId y usuario logueado
     * 
     * @param solucionId
     * @param pageable
     * @return Flux<SolicitudDto>
     */
    Flux<SolicitudDto> findAllByUsuarioAndSolucionId(String solucionIds, Pageable pageable);

    /**
     * Consulta la lista de solicitudes asociadas a la lista de solucionIds y
     * usuario logueado
     * 
     * @param solucionIds
     * @param pageable
     * @return Flux<SolicitudDto>
     */
    Flux<SolicitudDto> findAllByAllSoluciones(List<String> solucionIds, Pageable pageable);

    /**
     * Regresa un true si encuentra una solicitud que cumpla con los filtros
     * 
     * @param filtro filtro de busqueda
     * @return Mono<Boolean>
     */
    Mono<Boolean> exists(Filtro filtro);

    /**
     * Elimina la solicitud por id
     *
     * @param El id de la solicitud
     * @return Mono<Void>
     */
    Mono<Void> delete(String id);
}
