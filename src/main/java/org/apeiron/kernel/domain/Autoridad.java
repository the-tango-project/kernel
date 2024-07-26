package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * El objeto Autoridad representa los permisos que un usuario tiene para
 * consultar, modificar o eliminar en una Solucion
 * {@link org.apeiron.kernel.domain.Solucion}. Los permisos
 * pueden variar y están determinados por el rol que se le asigna al usuario
 * {@link org.apeiron.kernel.domain.enumeration.RolAutoridad}
 * La autoridad es una persona física específica con un rol. A diferencia de la
 * entidad Permiso {@link org.apeiron.kernel.domain.proceso.Permiso} que
 * sólo describe como se comporta un rol, la autoridad siempre debe ser un
 * usuario registrado en el sistema.
 *
 * @version 1.0
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autoridad implements Serializable {

    @Field("usuarioId")
    private String usuarioId;

    @Field("nombre")
    private String nombre;

    @Field("apellido_materno")
    private String apellidoMaterno;

    @Field("apellido_paterno")
    private String apellidoPaterno;

    // TODO: quitar esta propiedad
    @Field("cvu")
    private String cvu;

    @Field("roles")
    private List<RolAutoridad> roles;
}
