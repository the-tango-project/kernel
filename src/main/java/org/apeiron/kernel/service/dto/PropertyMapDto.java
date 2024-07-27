package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.commons.annotations.PropertyMapType;
import org.apeiron.kernel.domain.PropertyMap;

/**
 * Elemento Dto del elemento de dominio {@link PropertyMap}
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyMapDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Tipo de mapeo representado por {@link PropertyMapType}
     */
    private PropertyMapType type;

    /**
     * variable que representa la ruta de donde se debe de extraer el
     * valor que se desea mapear
     */
    private String from;

    /**
     * variable que representa la ruta destino en donde se debe de copiar el valor
     * de la variable 'from'
     */
    private String to;

    /**
     * Cuando el tipo del mapeo es de {@link PropertyMapType#FROM_INLINE_VALUE} en
     * esta variable se guarda el valor que se debe de asignar a la variable 'to'
     */
    private String value;
}
