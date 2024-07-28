package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.TipoAviso;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The Aviso class that represent an information message for the user into the
 * framework
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aviso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("titulo")
    private String titulo;

    @Field("descripcion")
    private String descripcion;

    @Field("pieDePagina")
    private String pieDePagina;

    @Field("tipo")
    private TipoAviso tipo;
}
