package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A SolicitudResumen.
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitudResumen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("solucion_id")
    private String solucionId;

    @Field("solicitud_id")
    private String solicitudId;

    @Field("nombre")
    private String nombre;
}
