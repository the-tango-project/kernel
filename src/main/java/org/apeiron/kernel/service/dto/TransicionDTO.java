package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.TipoAccion;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransicionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoAccion accion;

    private EstadoSolicitud destino;

    private String mensaje;

    private boolean confirmar;

    private boolean agregarComentario;

    private List<RuleDTO> reglas = new ArrayList<>();

    private List<ActionDTO> acciones = new ArrayList<>();

    private NotificacionDTO notificacion = new NotificacionDTO();
}
