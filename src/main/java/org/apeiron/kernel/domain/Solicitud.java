package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Solicitud.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Document(collection = "solicitudes")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Solicitud extends AbstractAuditingEntity<String> implements AbstractExtensible {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("nombre")
    private String nombre;

    // FIXME: Cambiar por usuarioId o agregar otra propiedad para tener un id y un
    // nombre de usuario para identificar a una persona
    @Field("usuario")
    private String usuario;

    @Field("solucionId")
    private String solucionId;

    @Field("estado")
    private EstadoSolicitud estado;

    @Field("solicitante")
    private Persona solicitante;

    @Field("revisores")
    private List<RevisorResumen> revisores = new ArrayList<>();

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
