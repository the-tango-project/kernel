package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoRevision;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Revisor.
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RevisorResumen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("id")
    private String id;

    @Field("evaluacion_id")
    private String evaluacionId;

    // FIXME: Cambiar por usuarioId
    @Field("cvu")
    private String cvu;

    @Field("revisor_id")
    private String revisorId;

    @Field("nombre")
    private String nombre;

    @Field("estado")
    private EstadoRevision estado;
}
