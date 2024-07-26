package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Documento;
import org.apeiron.kernel.service.dto.DocumentoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Documento} and its DTO {@link DocumentoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentoMapper extends EntityMapper<DocumentoDTO, Documento> {}
