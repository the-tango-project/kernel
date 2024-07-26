package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Button;
import org.apeiron.kernel.service.dto.ButtonDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Button} and its DTO {@link ButtonDTO}.
 */
@Mapper(componentModel = "spring")
public interface ButtonMapper extends EntityMapper<ButtonDTO, Button> {}
