package org.apeiron.kernel.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A Dto for the {@link mx.conacyt.domain.Documento} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DocumentoDto extends AbstractExtensibleDto {

    private String id;
}
