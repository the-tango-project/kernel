package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.service.dto.SolicitudDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Solicitud} and its Dto {@link SolicitudDto}.
 */
@Mapper(componentModel = "spring", uses = { RevisorResumenMapper.class, PersonaMapper.class })
public interface SolicitudMapper extends EntityMapper<SolicitudDto, Solicitud> {}
