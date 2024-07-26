package org.apeiron.kernel.domain.proceso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apeiron.kernel.domain.Aviso;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.apeiron.kernel.domain.enumeration.TipoAccion;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permiso implements Serializable {

    @Field("rol")
    private RolAutoridad rol;

    @Field("aviso")
    private Aviso aviso = new Aviso();

    @Field("tipo")
    private List<TipoAccion> acciones = new ArrayList<>();
}
