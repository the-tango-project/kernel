package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Rule;
import org.apeiron.kernel.service.dto.RuleDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Rule} and its DTO {@link RuleDTO}.
 */
@Mapper(componentModel = "spring", uses = { ArgumentMapper.class })
public interface RuleMapper extends EntityMapper<RuleDTO, Rule> {}
