package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Resultado;
import org.apeiron.kernel.service.dto.ResultadoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Resultado} and its DTO {@link ResultadoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResultadoMapper extends EntityMapper<ResultadoDTO, Resultado> {}
