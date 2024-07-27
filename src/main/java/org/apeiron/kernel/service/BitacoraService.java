package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.BitacoraDto;
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
     * @param bitacoraDto the entity to save.
     * @return the persisted entity.
     */
    Mono<BitacoraDto> save(BitacoraDto bitacoraDto);

    /**
     * Save a bitacora.
     *
     * @param contexto the entity to save.
     * @return the persisted entity.
     */
    Disposable saveAsynchronous(BitacoraDto bitacoraDto);

    /**
     * Get all the bitacoras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<BitacoraDto> findAll(Pageable pageable);

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
    Mono<BitacoraDto> findOne(String id);

    /**
     * Get all the bitacoras by Filtro.
     * @param filtro the filtro of the entity.
     * @return the list of entities.
     */
    Flux<BitacoraDto> findAll(Filtro filtro);

    /**
     * Get all the bitacoras by Filtro and Pageable.
     * @param filtro the filtro of the entity.
     * @param sort the sort of the entity.
     * @return the list of entities.
     */
    Flux<BitacoraDto> findAll(Filtro filtro, Sort sort);
}
