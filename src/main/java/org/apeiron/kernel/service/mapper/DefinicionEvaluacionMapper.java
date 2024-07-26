package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.DefinicionEvaluacion;
import org.apeiron.kernel.service.dto.DefinicionEvaluacionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DefinicionEvaluacion} and its DTO
 * {@link DefinicionEvaluacionDTO}.
 */
@Mapper(componentModel = "spring", uses = { DefinicionRevisionMapper.class })
public interface DefinicionEvaluacionMapper extends EntityMapper<DefinicionEvaluacionDTO, DefinicionEvaluacion> {}
