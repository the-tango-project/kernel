package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.SolicitudResumen;
import org.apeiron.kernel.service.dto.SolicitudResumenDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SolicitudResumen} and its DTO {@link SolicitudResumenDTO}.
 */
@Mapper(componentModel = "spring")
public interface SolicitudResumenMapper extends EntityMapper<SolicitudResumenDTO, SolicitudResumen> {}
