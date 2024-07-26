package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Reporte;
import org.apeiron.kernel.service.dto.ReporteDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Reporte} and its DTO {@link ReporteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReporteMapper extends EntityMapper<ReporteDTO, Reporte> {}
