package org.apeiron.kernel.service.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import org.apeiron.kernel.domain.enumeration.TipoAccion;

@Data
@Builder
public class ContextDto {

    private SolicitudDto solicitud;
    private SolucionDto solucion;
    private TipoAccion accion;
    private EstadoDto estadoActual;
    private TransicionDto transicion;
    private TransicionContextDto transicionContext;
    private String usuario;
    private Instant inicioTransicion;
    private String formId;
}
