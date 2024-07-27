package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.SolucionConfiguracion;
import org.apeiron.kernel.service.dto.SolucionConfiguracionDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link SolucionConfiguracion} and its Dto
 * {@link SolucionConfiguracionDto}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SolucionConfiguracionMapper extends EntityMapper<SolucionConfiguracionDto, SolucionConfiguracion> {}
