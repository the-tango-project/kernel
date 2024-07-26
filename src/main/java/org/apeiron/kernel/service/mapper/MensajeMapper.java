package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Mensaje;
import org.apeiron.kernel.service.dto.MensajeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Mensaje} and its DTO {@link MensajeDTO}.
 */
@Mapper(componentModel = "spring")
public interface MensajeMapper extends EntityMapper<MensajeDTO, Mensaje> {}
