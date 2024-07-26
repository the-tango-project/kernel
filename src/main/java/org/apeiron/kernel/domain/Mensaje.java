package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("bienvenida")
    private String bienvenida;

    @Field("terminos")
    private String terminos;

    @Field("bienvenidaRevision")
    private String bienvenidaRevision;

    @Field("terminosRevision")
    private String terminosRevision;

    @Field("bienvenidaOperador")
    private String bienvenidaOperador;
}
