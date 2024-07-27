package org.apeiron.kernel.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - Dto type parameter.
 * @param <E> - Entity type parameter.
 */
public interface ModifyMapper<D, E> {
    /**
     * Map a Dto to an Entity object.
     *
     * @param dto Dto to map.
     * @return Entity mapped.
     */
    E toEntity(D dto);

    /**
     * Map a List of Dtos to Entities.
     *
     * @param dtoList List of Dtos to map
     * @return List of Entities mapped
     */
    default List<E> toEntity(List<D> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return new ArrayList<>(0);
        } else {
            return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
        }
    }

    /**
     * Map Flux Dto to Flux Entity.
     *
     * @param flux Flux to map.
     * @return Flux mapped.
     */
    default Flux<E> fromFluxDto(Flux<D> flux) {
        if (flux == null) {
            return Flux.empty();
        } else {
            return flux.switchIfEmpty(Flux.empty()).mapNotNull(this::toEntity);
        }
    }

    /**
     * Map Mono Dto to Mono Entity.
     *
     * @param mono Mono to map.
     * @return Mono mapped.
     */
    default Mono<E> fromMonoDto(Mono<D> mono) {
        if (mono == null) {
            return Mono.empty();
        } else {
            return mono.switchIfEmpty(Mono.empty()).mapNotNull(this::toEntity);
        }
    }
}
