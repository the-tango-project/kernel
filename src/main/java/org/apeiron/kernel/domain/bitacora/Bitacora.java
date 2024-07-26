package org.apeiron.kernel.domain.bitacora;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.TipoAccion;
import org.apeiron.kernel.domain.enumeration.TipoMovimiento;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * La bitácora del sistema
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
@Builder
@Document(collection = "bitacora")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("tipo")
    private TipoMovimiento tipo;

    @Field("usuario_id")
    private String usuarioId;

    @Field("solicitudId")
    private String solicitudId;

    @Field("descripcion")
    private String descripcion;

    @Field("accion")
    private TipoAccion accion;

    @Field("estadoInicial")
    private EstadoSolicitud estadoInicial;

    @Field("estadoFinal")
    private EstadoSolicitud estadoFinal;

    @Field("fecha_creacion")
    private Instant fechaCreacion;
}
