package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Rule;
import org.apeiron.kernel.domain.proceso.Transicion;
import org.apeiron.kernel.service.dto.RuleDTO;
import org.apeiron.kernel.service.dto.TransicionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Rule} and its DTO {@link RuleDTO}.
 */
@Mapper(componentModel = "spring", uses = { NotificacionMapper.class })
public interface TransicionMapper extends EntityMapper<TransicionDTO, Transicion> {}
