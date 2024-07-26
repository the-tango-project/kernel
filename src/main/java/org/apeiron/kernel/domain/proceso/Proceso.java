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
public class Proceso implements Serializable {

    @Field("inicio")
    private EstadoSolicitud inicio;

    @Field("estados")
    private List<Estado> estados = new ArrayList<>();
}
