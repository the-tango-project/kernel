package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Persona;
import org.apeiron.kernel.service.dto.PersonaDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Persona} and its Dto {@link PersonaDto}.
 */
@Mapper(componentModel = "spring")
public interface PersonaMapper extends EntityMapper<PersonaDto, Persona> {}
