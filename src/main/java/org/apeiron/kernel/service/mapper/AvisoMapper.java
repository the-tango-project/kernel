package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Aviso;
import org.apeiron.kernel.service.dto.AvisoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Aviso} and its DTO {@link AvisoDTO}.
 */
@Mapper(componentModel = "spring")
public interface AvisoMapper extends EntityMapper<AvisoDTO, Aviso> {}
