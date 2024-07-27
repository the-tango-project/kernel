package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Form;
import org.apeiron.kernel.service.dto.FormDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Form} and its Dto {@link FormDto}.
 */
@Mapper(componentModel = "spring")
public interface FormMapper extends EntityMapper<FormDto, Form> {}
