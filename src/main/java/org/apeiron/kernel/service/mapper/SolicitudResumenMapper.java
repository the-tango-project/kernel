package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.SolicitudResumen;
import org.apeiron.kernel.service.dto.SolicitudResumenDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SolicitudResumen} and its Dto {@link SolicitudResumenDto}.
 */
@Mapper(componentModel = "spring")
public interface SolicitudResumenMapper extends EntityMapper<SolicitudResumenDto, SolicitudResumen> {}
