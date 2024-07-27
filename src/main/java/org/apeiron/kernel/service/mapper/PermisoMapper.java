package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.proceso.Permiso;
import org.apeiron.kernel.service.dto.PermisoDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Permiso} and its Dto {@link PermisoDto}.
 */
@Mapper(componentModel = "spring")
public interface PermisoMapper extends EntityMapper<PermisoDto, Permiso> {}
