package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Revision;
import org.apeiron.kernel.service.dto.RevisionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Revision} and its DTO {@link RevisionDTO}.
 */
@Mapper(componentModel = "spring")
public interface RevisionMapper extends EntityMapper<RevisionDTO, Revision> {}
