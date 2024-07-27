package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoRevision;

/**
 * A Dto for the {@link org.apeiron.kernel.domain.Revision} entity.
 */
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RevisionDto implements Serializable {

    @EqualsAndHashCode.Include
    private String id;

    private String evaluacionId;

    private EstadoRevision estado;

    private RespuestaDto respuesta;

    private RevisorDto revisor;

    private SolicitudResumenDto solicitudResumen;
}
