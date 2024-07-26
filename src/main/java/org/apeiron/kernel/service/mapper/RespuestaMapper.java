package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Respuesta;
import org.apeiron.kernel.service.dto.RespuestaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Respuesta} and its DTO {@link RespuestaDTO}.
 */
@Mapper(componentModel = "spring")
public interface RespuestaMapper extends EntityMapper<RespuestaDTO, Respuesta> {}
