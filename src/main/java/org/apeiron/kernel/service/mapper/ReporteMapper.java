package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Reporte;
import org.apeiron.kernel.service.dto.ReporteDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Reporte} and its Dto {@link ReporteDto}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReporteMapper extends EntityMapper<ReporteDto, Reporte> {}
