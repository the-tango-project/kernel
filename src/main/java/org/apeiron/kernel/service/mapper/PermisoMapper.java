package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.proceso.Permiso;
import org.apeiron.kernel.service.dto.PermisoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Permiso} and its DTO {@link PermisoDTO}.
 */
@Mapper(componentModel = "spring")
public interface PermisoMapper extends EntityMapper<PermisoDTO, Permiso> {}
