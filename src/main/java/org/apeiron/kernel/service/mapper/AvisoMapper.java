package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Aviso;
import org.apeiron.kernel.service.dto.AvisoDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Aviso} and its Dto {@link AvisoDto}.
 */
@Mapper(componentModel = "spring")
public interface AvisoMapper extends EntityMapper<AvisoDto, Aviso> {}
