package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.DefinicionEvaluacion;
import org.apeiron.kernel.service.dto.DefinicionEvaluacionDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DefinicionEvaluacion} and its Dto
 * {@link DefinicionEvaluacionDto}.
 */
@Mapper(componentModel = "spring", uses = { DefinicionRevisionMapper.class })
public interface DefinicionEvaluacionMapper extends EntityMapper<DefinicionEvaluacionDto, DefinicionEvaluacion> {}
