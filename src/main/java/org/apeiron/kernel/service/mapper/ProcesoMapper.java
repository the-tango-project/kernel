package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.proceso.Proceso;
import org.apeiron.kernel.service.dto.ProcesoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Proceso} and its DTO {@link ProcesoDTO}.
 */
@Mapper(componentModel = "spring", uses = { AvisoMapper.class, TransicionMapper.class })
public interface ProcesoMapper extends EntityMapper<ProcesoDTO, Proceso> {}
