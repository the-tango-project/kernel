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
 * A DTO for the {@link org.apeiron.kernel.domain.RevisorResumen}
 * entity.
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RevisorResumenDTO implements Serializable {

    private String id;

    private String evaluacionId;

    // FIXME: Cambiar por usuarioId o revisorId o login
    private String cvu;

    private String revisorId;

    private String nombre;

    private EstadoRevision estado;
}
