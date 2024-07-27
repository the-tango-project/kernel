package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.PropertyMap;
import org.apeiron.kernel.service.dto.PropertyMapDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link PropertyMap} and its Dto {@link PropertyMapDto}.
 */
@Mapper(componentModel = "spring")
public interface PropertyMapMapper extends EntityMapper<PropertyMapDto, PropertyMap> {}
