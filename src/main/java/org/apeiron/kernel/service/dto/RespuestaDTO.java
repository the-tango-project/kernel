package org.apeiron.kernel.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Respuesta} entity.
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RespuestaDTO extends AbstractExtensibleDTO {}
