package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Componente;
import org.apeiron.kernel.domain.Configuracion;
import org.apeiron.kernel.service.dto.ConfiguracionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Componente} and its DTO
 * {@link ConfiguracionDTO}.
 */
@Mapper(componentModel = "spring", uses = { DocumentoConfiguracionMapper.class, RuleMapper.class })
public interface ConfiguracionMapper extends EntityMapper<ConfiguracionDTO, Configuracion> {}
