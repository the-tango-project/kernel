package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Action;
import org.apeiron.kernel.service.dto.ActionDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Action} and its Dto {@link ActionDto}.
 */
@Mapper(componentModel = "spring", uses = { ArgumentMapper.class })
public interface ActionMapper extends EntityMapper<ActionDto, Action> {}
