package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Comentario;
import org.apeiron.kernel.service.dto.ComentarioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comentario} and its DTO {@link ComentarioDTO}.
 */
@Mapper(componentModel = "spring")
public interface ComentarioMapper extends EntityMapper<ComentarioDTO, Comentario> {}
