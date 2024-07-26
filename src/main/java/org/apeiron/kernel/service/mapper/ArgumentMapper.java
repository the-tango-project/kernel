package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Argument;
import org.apeiron.kernel.service.dto.ArgumentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Argument} and its DTO {@link ArgumentDTO}.
 */
@Mapper(componentModel = "spring", uses = { PropertyMapMapper.class })
public interface ArgumentMapper extends EntityMapper<ArgumentDTO, Argument> {}
