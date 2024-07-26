package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.PropertyMap;
import org.apeiron.kernel.service.dto.PropertyMapDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link PropertyMap} and its DTO {@link PropertyMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface PropertyMapMapper extends EntityMapper<PropertyMapDTO, PropertyMap> {}
