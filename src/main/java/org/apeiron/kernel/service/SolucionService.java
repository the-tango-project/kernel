package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.DefinicionEvaluacionDto;
import org.apeiron.kernel.service.dto.SolucionDto;
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
     * @param solucionDto the entity to save.
     * @return the persisted entity.
     */
    Mono<SolucionDto> save(SolucionDto solucionDto);

    /**
     * Crear a new solucion.
     *
     * @param solucionDto the entity to save.
     * @return the persisted entity.
     */
    Mono<SolucionDto> create(SolucionDto solucionDto);

    /**
     * Updates a solucion.
     *
     * @param solucionDto the entity to update.
     * @return the persisted entity.
     */
    Mono<SolucionDto> update(SolucionDto solucionDto);

    /**
     * Publicar una solucion.
     *
     * @param solucionDto la solucion para publicar.
     * @return the solucion para persistir.
     */
    Mono<SolucionDto> publicar(SolucionDto solucionDto);

    /**
     * Archivar una solucion.
     *
     * @param solucionDto la solucion para publicar.
     * @return the solucion para persistir.
     */
    Mono<SolucionDto> archivar(SolucionDto solucionDto);

    /**
     * Partially updates a solucion.
     *
     * @param solucionDto the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SolucionDto> partialUpdate(SolucionDto solucionDto);

    /**
     * Get all the solucions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SolucionDto> findAll(Pageable pageable);

    /**
     * Get all the solucions.
     *
     * @param filtro the filter.
     * @return the list of entities.
     */
    Flux<SolucionDto> findAll(Filtro filtro);

    Flux<SolucionDto> findAll(Filtro filtro, Pageable pageable);

    /**
     * Get all the solucions by estado
     *
     * @param estado   the state of the solucion.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<SolucionDto> findAllByEstado(String estado, Pageable pageable);

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
    Mono<SolucionDto> findOne(String id);

    /**
     * Find the last solucion by "id" into the historical data.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SolucionDto> findOneByLastVersion(String id);

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
     * @return the DefinicionEvaluacionDto.
     */
    Mono<DefinicionEvaluacionDto> findOneDefinicionEvaluacion(String solucionId);

    /**
     * Save a definicionEvalucionDto.
     *
     * @param DefinicionEvaluacionDto the entity to save.
     * @return the persisted entity.
     */
    Mono<DefinicionEvaluacionDto> saveDefinicionEvaluacion(DefinicionEvaluacionDto definicionDto);
}
