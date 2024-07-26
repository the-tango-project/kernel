package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.BitacoraDTO;
import org.apeiron.kernel.service.util.Filtro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link mx.conacyt.domain.Bitacora}.
 */
public interface BitacoraService {
    /**
     * Save a bitacora.
     *
     * @param bitacoraDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<BitacoraDTO> save(BitacoraDTO bitacoraDTO);

    /**
     * Save a bitacora.
     *
     * @param contexto the entity to save.
     * @return the persisted entity.
     */
    Disposable saveAsynchronous(BitacoraDTO bitacoraDTO);

    /**
     * Get all the bitacoras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<BitacoraDTO> findAll(Pageable pageable);

    /**
     * Returns the number of bitacoras available.
     *
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" bitacora.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<BitacoraDTO> findOne(String id);

    /**
     * Get all the bitacoras by Filtro.
     * @param filtro the filtro of the entity.
     * @return the list of entities.
     */
    Flux<BitacoraDTO> findAll(Filtro filtro);

    /**
     * Get all the bitacoras by Filtro and Pageable.
     * @param filtro the filtro of the entity.
     * @param sort the sort of the entity.
     * @return the list of entities.
     */
    Flux<BitacoraDTO> findAll(Filtro filtro, Sort sort);
}
