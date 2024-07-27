package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Rule;
import org.apeiron.kernel.service.dto.RuleDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Rule} and its Dto {@link RuleDto}.
 */
@Mapper(componentModel = "spring", uses = { ArgumentMapper.class })
public interface RuleMapper extends EntityMapper<RuleDto, Rule> {}
