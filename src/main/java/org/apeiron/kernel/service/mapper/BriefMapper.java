package org.apeiron.kernel.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contract for a generic entity to brief dto mapper.
 *
 * @param <D> - BriefDTO type parameter.
 * @param <E> - Entity type parameter.
 */
public interface BriefMapper<D, E> {
    /**
     * Map an Entity to a DTO object.
     *
     * @param entity Entity to map.
     * @return DTO mapped.
     */
    D toDto(E entity);

    /**
     * Map a List of Entities to DTOs.
     *
     * @param entityList List of Entities to map.
     * @return List of DTOs mapped.
     */
    default List<D> toDto(List<E> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return new ArrayList<>(0);
        } else {
            return entityList.stream().map(this::toDto).collect(Collectors.toList());
        }
    }

    /**
     * Map Flux Entity to Flux DTO.
     *
     * @param flux Flux to map.
     * @return Flux mapped.
     */
    default Flux<D> fromFluxEntity(Flux<E> flux) {
        if (flux == null) {
            return Flux.empty();
        } else {
            return flux.switchIfEmpty(Flux.empty()).mapNotNull(this::toDto);
        }
    }

    /**
     * Map Mono Entity to Mono DTO.
     *
     * @param mono Mono to map.
     * @return Mono mapped.
     */
    default Mono<D> fromMonoEntity(Mono<E> mono) {
        if (mono == null) {
            return Mono.empty();
        } else {
            return mono.switchIfEmpty(Mono.empty()).mapNotNull(this::toDto);
        }
    }
}
