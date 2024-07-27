package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Resultado;
import org.apeiron.kernel.service.dto.ResultadoDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Resultado} and its Dto {@link ResultadoDto}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResultadoMapper extends EntityMapper<ResultadoDto, Resultado> {}
