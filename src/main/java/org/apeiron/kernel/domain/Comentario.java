package org.apeiron.kernel.domain;

import com.querydsl.core.annotations.QueryEntity;
import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.apeiron.kernel.domain.enumeration.TipoComentario;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Comentario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
@QueryEntity
@Document(collection = "comentarios")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("usuario_id")
    private String usuarioId;

    @Field("text")
    private String text;

    @Field("tipo")
    private TipoComentario tipo;

    @Field("fecha_creacion")
    private Instant fechaCreacion = Instant.now();

    // FIXME: Refactorizar para que esta propiedad cumpla con los lineamientos Java
    @Field("leido")
    private Boolean leido;

    @Field("rol")
    private RolAutoridad rol;

    @Field("solicitudId")
    private String solicitudId;
}
