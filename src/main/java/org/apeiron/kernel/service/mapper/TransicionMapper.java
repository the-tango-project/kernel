package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Rule;
import org.apeiron.kernel.domain.proceso.Transicion;
import org.apeiron.kernel.service.dto.RuleDto;
import org.apeiron.kernel.service.dto.TransicionDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Rule} and its Dto {@link RuleDto}.
 */
@Mapper(componentModel = "spring", uses = { NotificacionMapper.class })
public interface TransicionMapper extends EntityMapper<TransicionDto, Transicion> {}
