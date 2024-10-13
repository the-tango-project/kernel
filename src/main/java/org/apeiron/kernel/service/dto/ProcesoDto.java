package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private EstadoSolicitud inicio;

    private List<RolAutoridad> roles = new ArrayList<>();

    private List<EstadoDto> estados = new ArrayList<>();
}
