package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Revision;
import org.apeiron.kernel.service.dto.RevisionDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Revision} and its Dto {@link RevisionDto}.
 */
@Mapper(componentModel = "spring")
public interface RevisionMapper extends EntityMapper<RevisionDto, Revision> {}
