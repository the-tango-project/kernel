package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Calendario;
import org.apeiron.kernel.service.dto.CalendarioDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Calendario} and its Dto {@link CalendarioDto}.
 */
@Mapper(componentModel = "spring")
public interface CalendarioMapper extends EntityMapper<CalendarioDto, Calendario> {}
