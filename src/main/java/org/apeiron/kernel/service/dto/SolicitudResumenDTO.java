package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.SolicitudResumen} entity.
 */
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitudResumenDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String solucionId;

    @EqualsAndHashCode.Include
    private String solicitudId;

    private String nombre;
}
