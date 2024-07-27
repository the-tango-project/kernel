package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.proceso.Proceso;
import org.apeiron.kernel.service.dto.ProcesoDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Proceso} and its Dto {@link ProcesoDto}.
 */
@Mapper(componentModel = "spring", uses = { AvisoMapper.class, TransicionMapper.class })
public interface ProcesoMapper extends EntityMapper<ProcesoDto, Proceso> {}
