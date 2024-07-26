package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Form;
import org.apeiron.kernel.service.dto.FormDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Form} and its DTO {@link FormDTO}.
 */
@Mapper(componentModel = "spring")
public interface FormMapper extends EntityMapper<FormDTO, Form> {}
