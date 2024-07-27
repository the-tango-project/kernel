package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoridadDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private String usuarioId;

    private String nombre;

    private String apellidoMaterno;

    private String apellidoPaterno;

    //FIXME: Eliminar y sólo dejar usuarioId o uno más genérico
    private String cvu;

    private List<RolAutoridad> roles;
}
