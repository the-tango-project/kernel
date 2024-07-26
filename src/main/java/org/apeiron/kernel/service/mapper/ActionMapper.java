package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Action;
import org.apeiron.kernel.service.dto.ActionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Action} and its DTO {@link ActionDTO}.
 */
@Mapper(componentModel = "spring", uses = { ArgumentMapper.class })
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {}
