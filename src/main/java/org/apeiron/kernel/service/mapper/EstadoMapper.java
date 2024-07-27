package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.proceso.Estado;
import org.apeiron.kernel.service.dto.EstadoDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Estado} and its Dto {@link EstadoDto}.
 */
@Mapper(componentModel = "spring", uses = { NotificacionMapper.class })
public interface EstadoMapper extends EntityMapper<EstadoDto, Estado> {}
