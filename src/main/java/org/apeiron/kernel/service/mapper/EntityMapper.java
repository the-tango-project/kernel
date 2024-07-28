package org.apeiron.kernel.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Contract for a generic mapper between Dto and Entity.
 *
 * @param <D> - Dto type parameter.
 * @param <E> - Entity type parameter.
 */
public interface EntityMapper<D, E> extends BriefMapper<D, E>, ModifyMapper<D, E> {
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    /**
     * The method `partialUpdate` in the `EntityMapper` interface is a custom
     * mapping method defined using MapStruct annotations.
     * 
     * @param entity
     * @param dto
     */
    void partialUpdate(@MappingTarget E entity, D dto);
}
