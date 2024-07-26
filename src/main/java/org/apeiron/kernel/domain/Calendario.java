package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * El objeto Calendario contiene las fechas importantes y válidas para la
 * apertura de una convocatoria, límite de recepción de solicitudes y límite
 * para recibir revisiones
 * {@link org.apeiron.kernel.domain.enumeration.RolAutoridad}
 *
 * FIXME: Refactorizar esta clase de tal manera que el calendario sea dinámico
 * 
 * @version 1.0
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("fechaInicio")
    private Instant fechaInicio;

    @Field("fechaFinSolicitud")
    private Instant fechaFinSolicitud;

    @Field("fechaFinRevision")
    private Instant fechaFinRevision;

    /**
     * Fecha límite para que el usuario pueda solicitar una reconsideración
     */
    @Field("fechaFinReconsideracion")
    private Instant fechaFinReconsideracion;
}
