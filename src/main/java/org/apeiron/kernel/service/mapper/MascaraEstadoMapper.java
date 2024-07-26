package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.MascaraEstado;
import org.apeiron.kernel.service.dto.MascaraEstadoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link MascaraEstado} and its DTO
 * {@link MascaraEstadoDTO}.
 */
@Mapper(componentModel = "spring")
public interface MascaraEstadoMapper extends EntityMapper<MascaraEstadoDTO, MascaraEstado> {}
