package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Columna;
import org.apeiron.kernel.service.dto.ColumnaDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Columna} and its Dto {@link ColumnaDto}.
 */
@Mapper(componentModel = "spring")
public interface ColumnaMapper extends EntityMapper<ColumnaDto, Columna> {}
