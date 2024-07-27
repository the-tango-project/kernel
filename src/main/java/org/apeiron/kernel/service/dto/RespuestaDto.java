package org.apeiron.kernel.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A Dto for the {@link org.apeiron.kernel.domain.Respuesta} entity.
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RespuestaDto extends AbstractExtensibleDto {}
