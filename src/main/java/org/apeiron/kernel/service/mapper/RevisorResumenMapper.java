package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Revisor;
import org.apeiron.kernel.domain.RevisorResumen;
import org.apeiron.kernel.service.dto.RevisorDto;
import org.apeiron.kernel.service.dto.RevisorResumenDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Revisor} and its Dto {@link RevisorDto}.
 */
@Mapper(componentModel = "spring")
public interface RevisorResumenMapper extends EntityMapper<RevisorResumenDto, RevisorResumen> {}
