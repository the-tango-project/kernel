package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.MenuElement;

/**
 * A Dto for the {@link org.apeiron.kernel.domain.Componente} entity.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ComponenteDto implements Serializable {

    private Integer orden;

    private String titulo;

    private String descripcion;

    private String formId;

    private String icon;

    private String tipo;

    private String path;

    private MenuElement menuName;

    private ConfiguracionDto configuracion = new ConfiguracionDto();
}
