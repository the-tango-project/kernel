package org.apeiron.kernel.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Revision} entity.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResultadoDTO extends AbstractExtensibleDTO {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private String id;

    private String convocatoria;

    private String motivo;

    private String nivel;

    private String solicitudUrl;

    private String perfilUrl;
}
