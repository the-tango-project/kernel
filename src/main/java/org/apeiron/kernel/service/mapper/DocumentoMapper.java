package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Documento;
import org.apeiron.kernel.service.dto.DocumentoDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Documento} and its Dto {@link DocumentoDto}.
 */
@Mapper(componentModel = "spring")
public interface DocumentoMapper extends EntityMapper<DocumentoDto, Documento> {}
