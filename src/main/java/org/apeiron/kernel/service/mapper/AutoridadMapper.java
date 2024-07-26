package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Autoridad;
import org.apeiron.kernel.service.dto.AutoridadDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Autoridad} and its DTO {@link AutoridadDTO}.
 */
@Mapper(componentModel = "spring")
public interface AutoridadMapper extends EntityMapper<AutoridadDTO, Autoridad> {}
