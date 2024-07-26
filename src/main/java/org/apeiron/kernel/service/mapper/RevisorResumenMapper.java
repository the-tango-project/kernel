package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Revisor;
import org.apeiron.kernel.domain.RevisorResumen;
import org.apeiron.kernel.service.dto.RevisorDTO;
import org.apeiron.kernel.service.dto.RevisorResumenDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Revisor} and its DTO {@link RevisorDTO}.
 */
@Mapper(componentModel = "spring")
public interface RevisorResumenMapper extends EntityMapper<RevisorResumenDTO, RevisorResumen> {}
