package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.MenuElement;
import org.apeiron.kernel.domain.enumeration.TipoComponente;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Componente.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Componente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("orden")
    private Integer orden;

    @Field("titulo")
    private String titulo;

    @Field("descripcion")
    private String descripcion;

    @Field("form_id")
    private String formId;

    @Field("icon")
    private String icon;

    @Field("tipo")
    private TipoComponente tipo;

    @Field("path")
    private String path;

    @Field("menu_name")
    private MenuElement menuName;

    @Field("configuracion")
    private Configuracion configuracion = new Configuracion();
}
