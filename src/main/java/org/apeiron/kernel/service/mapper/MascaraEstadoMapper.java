package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.MascaraEstado;
import org.apeiron.kernel.service.dto.MascaraEstadoDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link MascaraEstado} and its Dto
 * {@link MascaraEstadoDto}.
 */
@Mapper(componentModel = "spring")
public interface MascaraEstadoMapper extends EntityMapper<MascaraEstadoDto, MascaraEstado> {}
