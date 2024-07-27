package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Mensaje;
import org.apeiron.kernel.service.dto.MensajeDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Mensaje} and its Dto {@link MensajeDto}.
 */
@Mapper(componentModel = "spring")
public interface MensajeMapper extends EntityMapper<MensajeDto, Mensaje> {}
