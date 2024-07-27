package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Componente;
import org.apeiron.kernel.domain.DocumentoConfiguracion;
import org.apeiron.kernel.service.dto.ConfiguracionDto;
import org.apeiron.kernel.service.dto.DocumentoConfiguracionDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Componente} and its Dto
 * {@link ConfiguracionDto}.
 */
@Mapper(componentModel = "spring")
public interface DocumentoConfiguracionMapper extends EntityMapper<DocumentoConfiguracionDto, DocumentoConfiguracion> {}
