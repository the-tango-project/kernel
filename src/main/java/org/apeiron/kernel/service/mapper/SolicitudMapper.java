package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.service.dto.SolicitudDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Solicitud} and its DTO {@link SolicitudDTO}.
 */
@Mapper(componentModel = "spring", uses = { RevisorResumenMapper.class, PersonaMapper.class })
public interface SolicitudMapper extends EntityMapper<SolicitudDTO, Solicitud> {}
