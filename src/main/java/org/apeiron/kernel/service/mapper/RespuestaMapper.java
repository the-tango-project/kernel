package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Respuesta;
import org.apeiron.kernel.service.dto.RespuestaDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Respuesta} and its Dto {@link RespuestaDto}.
 */
@Mapper(componentModel = "spring")
public interface RespuestaMapper extends EntityMapper<RespuestaDto, Respuesta> {}
