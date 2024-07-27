package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.VistaResumen;
import org.apeiron.kernel.service.dto.VistaResumenDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link VistaResumen} and its Dto
 * {@link VistaResumenDto}.
 */
@Mapper(componentModel = "spring", uses = { ColumnaMapper.class, ButtonMapper.class, MascaraEstadoMapper.class })
public interface VistaResumenMapper extends EntityMapper<VistaResumenDto, VistaResumen> {}
