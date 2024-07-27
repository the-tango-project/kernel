package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Notificacion;
import org.apeiron.kernel.service.dto.NotificacionDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Notificacion} and its Dto
 * {@link NotificacionDto}.
 */
@Mapper(componentModel = "spring")
public interface NotificacionMapper extends EntityMapper<NotificacionDto, Notificacion> {}
