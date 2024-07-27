package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.DefinicionRevision;
import org.apeiron.kernel.service.dto.DefinicionRevisionDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DefinicionRevision} and its Dto
 * {@link DefinicionRevisionDto}.
 */
@Mapper(componentModel = "spring", uses = { ComponenteMapper.class })
public interface DefinicionRevisionMapper extends EntityMapper<DefinicionRevisionDto, DefinicionRevision> {}
