package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.Rule;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.validator.IRule;
import org.apeiron.kernel.utils.StringUtils;

/**
 * DTO para el elemento de dominio {@link Rule}.
 */
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RuleDTO implements Parametrizable, Serializable {

    /**
     * Clave que se le asigna a una anotación como referencia. Este valor se
     * construye a partir del nombre de la clase que implementa una anotación
     * {@link IRule} o {@link IAction} en formato CamelCase. Para estandarizar el
     * nombre de la clave, se utiliza la función
     * {@link StringUtils#toCamelCase(String)} al nombre simple de la clase
     * {@link Class#getSimpleName()}
     *
     * <pre>{@code
     * String clave = StringUtils.toCamelCase(clazz.getSimpleName());
     * }</pre>
     */
    @EqualsAndHashCode.Include
    private String clave;

    /**
     * Nombre asignado a la acción
     */
    private String nombre;

    /**
     * La condición es un texto que describe la condición a
     * partir de la cual la regla que se está definiendo es valida
     */
    private String condicion;

    /**
     * Variable que indica si la regla se encuentra deprecated o no
     */
    private boolean deprecated;

    /**
     * Arreglo de argumentos que se le pueden pasar a una regla para su evaluación
     */
    private ArgumentDTO[] arguments;

    /**
     * Arreglo de etiquetas para la regla
     */
    private String[] tags;
}
