package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Button;
import org.apeiron.kernel.service.dto.ButtonDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Button} and its Dto {@link ButtonDto}.
 */
@Mapper(componentModel = "spring")
public interface ButtonMapper extends EntityMapper<ButtonDto, Button> {}
