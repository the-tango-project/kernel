package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Persona;
import org.apeiron.kernel.service.dto.PersonaDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Persona} and its DTO {@link PersonaDTO}.
 */
@Mapper(componentModel = "spring")
public interface PersonaMapper extends EntityMapper<PersonaDTO, Persona> {}
