package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.TipoAccion;
import org.apeiron.kernel.domain.enumeration.TipoMovimiento;

/**
 * A DTO for the {@link mx.conacyt.domain.Documento} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BitacoraDTO implements Serializable {

    private String id;

    private TipoMovimiento tipo;

    private String usuarioId;

    private String solicitudId;

    private String descripcion;

    private TipoAccion accion;

    private EstadoSolicitud estadoInicial;

    private EstadoSolicitud estadoFinal;

    private Instant fechaCreacion;
}
