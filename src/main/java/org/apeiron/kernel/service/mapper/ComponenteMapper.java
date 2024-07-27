package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Componente;
import org.apeiron.kernel.service.dto.ComponenteDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Componente} and its Dto {@link ComponenteDto}.
 */
@Mapper(componentModel = "spring", uses = { ConfiguracionMapper.class })
public interface ComponenteMapper extends EntityMapper<ComponenteDto, Componente> {}
