package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.bitacora.Bitacora;
import org.apeiron.kernel.service.dto.BitacoraDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Bitacora} and its Dto {@link BitacoraDto}.
 */
@Mapper(componentModel = "spring")
public interface BitacoraMapper extends EntityMapper<BitacoraDto, Bitacora> {}
