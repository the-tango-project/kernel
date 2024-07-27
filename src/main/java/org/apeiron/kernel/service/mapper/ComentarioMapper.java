package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Comentario;
import org.apeiron.kernel.service.dto.ComentarioDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comentario} and its Dto {@link ComentarioDto}.
 */
@Mapper(componentModel = "spring")
public interface ComentarioMapper extends EntityMapper<ComentarioDto, Comentario> {}
