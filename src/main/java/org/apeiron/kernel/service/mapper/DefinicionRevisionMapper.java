package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.DefinicionRevision;
import org.apeiron.kernel.service.dto.DefinicionRevisionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DefinicionRevision} and its DTO
 * {@link DefinicionRevisionDTO}.
 */
@Mapper(componentModel = "spring", uses = { ComponenteMapper.class })
public interface DefinicionRevisionMapper extends EntityMapper<DefinicionRevisionDTO, DefinicionRevision> {}
