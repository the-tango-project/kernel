package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Columna;
import org.apeiron.kernel.service.dto.ColumnaDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Columna} and its DTO {@link ColumnaDTO}.
 */
@Mapper(componentModel = "spring")
public interface ColumnaMapper extends EntityMapper<ColumnaDTO, Columna> {}
