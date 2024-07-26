package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Componente;
import org.apeiron.kernel.service.dto.ComponenteDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Componente} and its DTO {@link ComponenteDTO}.
 */
@Mapper(componentModel = "spring", uses = { ConfiguracionMapper.class })
public interface ComponenteMapper extends EntityMapper<ComponenteDTO, Componente> {}
