package org.apeiron.kernel.domain.proceso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado implements Serializable {

    @Field("nombre")
    private EstadoSolicitud nombre;

    @Field("transiciones")
    private List<Transicion> transiciones = new ArrayList<>();

    @Field("permisos")
    private List<Permiso> permisos;

    @Field("digram")
    private Diagram digram = new Diagram();
}
