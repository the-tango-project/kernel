package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.proceso.Estado;
import org.apeiron.kernel.service.dto.EstadoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Estado} and its DTO {@link EstadoDTO}.
 */
@Mapper(componentModel = "spring", uses = { NotificacionMapper.class })
public interface EstadoMapper extends EntityMapper<EstadoDTO, Estado> {}
