package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Calendario;
import org.apeiron.kernel.service.dto.CalendarioDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Calendario} and its DTO {@link CalendarioDTO}.
 */
@Mapper(componentModel = "spring")
public interface CalendarioMapper extends EntityMapper<CalendarioDTO, Calendario> {}
