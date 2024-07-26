package org.apeiron.kernel.service.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import org.apeiron.kernel.domain.enumeration.TipoAccion;

@Data
@Builder
public class ContextDTO {

    private SolicitudDTO solicitud;
    private SolucionDTO solucion;
    private TipoAccion accion;
    private EstadoDTO estadoActual;
    private TransicionDTO transicion;
    private TransicionContextDTO transicionContext;
    private String usuario;
    private Instant inicioTransicion;
    private String formId;
}
