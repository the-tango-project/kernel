package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("nombre")
    private String nombre;

    @Field("nombre_propiedad")
    private String nombrePropiedad;

    @Field("requerido")
    private boolean requerido;

    @Field("max")
    private Integer max;

    @Field("mediaTypes")
    private List<String> mediaTypes = new ArrayList<>();

    @Field("expresion")
    private String expresion;
}
