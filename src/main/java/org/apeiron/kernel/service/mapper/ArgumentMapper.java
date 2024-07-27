package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Argument;
import org.apeiron.kernel.service.dto.ArgumentDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Argument} and its Dto {@link ArgumentDto}.
 */
@Mapper(componentModel = "spring", uses = { PropertyMapMapper.class })
public interface ArgumentMapper extends EntityMapper<ArgumentDto, Argument> {}
