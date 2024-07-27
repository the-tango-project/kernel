package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Autoridad;
import org.apeiron.kernel.service.dto.AutoridadDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Autoridad} and its Dto {@link AutoridadDto}.
 */
@Mapper(componentModel = "spring")
public interface AutoridadMapper extends EntityMapper<AutoridadDto, Autoridad> {}
