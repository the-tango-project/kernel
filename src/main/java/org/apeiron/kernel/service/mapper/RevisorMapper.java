package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Revisor;
import org.apeiron.kernel.service.dto.RevisorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Revisor} and its DTO {@link RevisorDTO}.
 */
@Mapper(componentModel = "spring")
public interface RevisorMapper extends EntityMapper<RevisorDTO, Revisor> {}
