package org.apeiron.kernel.domain.proceso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apeiron.kernel.domain.Action;
import org.apeiron.kernel.domain.Notificacion;
import org.apeiron.kernel.domain.Rule;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.TipoAccion;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transicion implements Serializable {

    @Field("accion")
    private TipoAccion accion;

    @Field("destino")
    private EstadoSolicitud destino;

    @Field("mensaje")
    private String mensaje;

    @Field("confirmar")
    private boolean confirmar;

    @Field("agregar_comentario")
    private boolean agregarComentario;

    @Field("reglas")
    private List<Rule> reglas = new ArrayList<>();

    @Field("acciones")
    private List<Action> acciones = new ArrayList<>();

    @Field("notificacion")
    private Notificacion notificacion = new Notificacion();

    @Field("diagram")
    private Diagram diagram = new Diagram(0.0, 0.0, "connector-b", "connector-h", "smoothstep");
}
