package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascaraEstadoDTO implements Serializable {

    private String id;

    private RolAutoridad rol;

    private EstadoSolicitud estado;

    private EstadoSolicitud mascara;

    private String mascaraString;
}
