package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Componente;
import org.apeiron.kernel.domain.DocumentoConfiguracion;
import org.apeiron.kernel.service.dto.ConfiguracionDTO;
import org.apeiron.kernel.service.dto.DocumentoConfiguracionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Componente} and its DTO
 * {@link ConfiguracionDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentoConfiguracionMapper extends EntityMapper<DocumentoConfiguracionDTO, DocumentoConfiguracion> {}
