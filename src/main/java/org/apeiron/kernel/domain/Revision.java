package org.apeiron.kernel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoRevision;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Revision.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Document(collection = "revisiones")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Revision extends AbstractAuditingEntity<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("evaluacion_id")
    private String evaluacionId;

    @Field("estado")
    private EstadoRevision estado;

    @Field("respuesta")
    private Respuesta respuesta;

    @Field("revisor")
    private Revisor revisor;

    @Field("solicitudResumen")
    private SolicitudResumen solicitudResumen;
}
