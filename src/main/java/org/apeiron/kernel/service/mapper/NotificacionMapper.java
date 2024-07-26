package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Notificacion;
import org.apeiron.kernel.service.dto.NotificacionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Notificacion} and its DTO
 * {@link NotificacionDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotificacionMapper extends EntityMapper<NotificacionDTO, Notificacion> {}
