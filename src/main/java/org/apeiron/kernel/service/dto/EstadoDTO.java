package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private EstadoSolicitud nombre;

    private List<TransicionDTO> transiciones = new ArrayList<>();

    private List<PermisoDTO> permisos = new ArrayList<>();
}
