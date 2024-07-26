package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.VistaResumen;
import org.apeiron.kernel.service.dto.VistaResumenDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link VistaResumen} and its DTO
 * {@link VistaResumenDTO}.
 */
@Mapper(componentModel = "spring", uses = { ColumnaMapper.class, ButtonMapper.class, MascaraEstadoMapper.class })
public interface VistaResumenMapper extends EntityMapper<VistaResumenDTO, VistaResumen> {}
