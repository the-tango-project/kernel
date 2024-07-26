package org.apeiron.kernel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MascaraEstado {

    @Field("id")
    private String id;

    @Field("rol")
    private RolAutoridad rol;

    @Field("estado")
    private EstadoSolicitud estado;

    @Field("mascara")
    private EstadoSolicitud mascara;

    @Field("mascara_string")
    private String mascaraString;
}
