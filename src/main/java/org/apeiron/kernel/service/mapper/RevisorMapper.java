package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Revisor;
import org.apeiron.kernel.service.dto.RevisorDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Revisor} and its Dto {@link RevisorDto}.
 */
@Mapper(componentModel = "spring")
public interface RevisorMapper extends EntityMapper<RevisorDto, Revisor> {}
