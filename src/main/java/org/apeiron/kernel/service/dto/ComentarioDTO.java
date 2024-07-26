package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.apeiron.kernel.domain.enumeration.TipoComentario;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Comentario} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ComentarioDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String id;

    private String usuarioId;

    private String text;

    private TipoComentario tipo;

    @Builder.Default
    private Instant fechaCreacion = Instant.now();

    private Boolean leido;

    private RolAutoridad rol;

    private String solicitudId;
}
