package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.Action;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.validator.IRule;
import org.apeiron.kernel.utils.StringUtils;

/**
 * Dto para el elemento de dominio {@link Action}
 */
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ActionDto implements Parametrizable, Serializable {

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
     * Variable que almacena el Optional de la anotación {@link Deprecated} en
     * caso de que exista su definición. Una {@link IRule} o {@link IAction} anotada
     * como deprecated significa que será removida en próximas versiones, por lo
     * tanto, se deberá de indicar la nueva {@link IRule} o {@link IAction} que se
     * será utilizada en su lugar.
     */
    private boolean deprecated;

    /**
     * Lista de argumentos que se le pueden pasar a una acción para procesarla
     */
    private ArgumentDto[] arguments;
}
