package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.DefinicionEvaluacionDTO;
import org.apeiron.kernel.service.dto.SolucionDTO;
import org.apeiron.kernel.service.util.Filtro;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing
 * {@link org.apeiron.kernel.domain.Solucion}.
 */
public interface SolucionService {
    /**
     * Save a solucion.
     *
     * @param solucionDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SolucionDTO> save(SolucionDTO solucionDTO);

    /**
     * Crear a new solucion.
     *
     * @param solucionDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SolucionDTO> create(SolucionDTO solucionDTO);

    /**
     * Updates a solucion.
     *
     * @param solucionDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SolucionDTO> update(SolucionDTO solucionDTO);

    /**
     * Publicar una solucion.
     *
     * @param solucionDTO la solucion para publicar.
     * @return the solucion para persistir.
     */
    Mono<SolucionDTO> publicar(SolucionDTO solucionDTO);

    /**
     * Archivar una solucion.
     *
     * @param solucionDTO la solucion para publicar.
     * @return the solucion para persistir.
     */
    Mono<SolucionDTO> archivar(SolucionDTO solucionDTO);

    /**
     * Partially updates a solucion.
     *
     * @param solucionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SolucionDTO> partialUpdate(SolucionDTO solucionDTO);

    /**
     * Get all the solucions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SolucionDTO> findAll(Pageable pageable);

    /**
     * Get all the solucions.
     *
     * @param filtro the filter.
     * @return the list of entities.
     */
    Flux<SolucionDTO> findAll(Filtro filtro);

    Flux<SolucionDTO> findAll(Filtro filtro, Pageable pageable);

    /**
     * Get all the solucions by estado
     *
     * @param estado   the state of the solucion.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<SolucionDTO> findAllByEstado(String estado, Pageable pageable);

    /**
     * Returns the number of solucions available.
     *
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    Mono<Long> countAll(Filtro filtro);

    /**
     * Get the "id" solucion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SolucionDTO> findOne(String id);

    /**
     * Find the last solucion by "id" into the historical data.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SolucionDTO> findOneByLastVersion(String id);

    /**
     * Delete the "id" solucion.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);

    /**
     * Get the "id" revision definition.
     *
     * @param solucionId the id of the solucion.
     * @return the DefinicionEvaluacionDTO.
     */
    Mono<DefinicionEvaluacionDTO> findOneDefinicionEvaluacion(String solucionId);

    /**
     * Save a definicionEvalucionDTO.
     *
     * @param DefinicionEvaluacionDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DefinicionEvaluacionDTO> saveDefinicionEvaluacion(DefinicionEvaluacionDTO definicionDTO);
}
