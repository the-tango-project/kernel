package org.apeiron.kernel.service.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apeiron.kernel.domain.enumeration.EstadoForm;
import org.apeiron.kernel.domain.enumeration.MenuElement;
import org.apeiron.kernel.domain.enumeration.TipoComponente;

/**
 * A Dto for the {@link org.apeiron.kernel.domain.Form} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class FormDto extends AbstractExtensibleDto {

    @EqualsAndHashCode.Include
    private String id;

    private EstadoForm estado;

    private String title;

    private MenuElement menuName;

    private String description;

    private TipoComponente tipo;

    private String icon;

    private String html;

    private String vuejs;

    private List<String> tags;
}
