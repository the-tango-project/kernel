package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.apeiron.kernel.domain.enumeration.TipoAccion;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PermisoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private RolAutoridad rol;

    private AvisoDto aviso = new AvisoDto();

    private List<TipoAccion> acciones = new ArrayList<>();
}
