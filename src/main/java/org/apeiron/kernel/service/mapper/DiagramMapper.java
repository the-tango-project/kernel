package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.proceso.Diagram;
import org.apeiron.kernel.service.dto.DiagramDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Diagram} and its Dto {@link DiagramDto}.
 */
@Mapper(componentModel = "spring")
public interface DiagramMapper extends EntityMapper<DiagramDto, Diagram> {
}
