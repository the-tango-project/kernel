package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.validator.IRule;
import org.apeiron.kernel.utils.StringUtils;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Elemento de dominio que representa la información requerida para definir a
 * una acción
 *
 */
@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Action implements Serializable {

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
    @Field("clave")
    private String clave;

    /**
     * Nombre asignado a la acción
     */
    @Field("nombre")
    private String nombre;

    /**
     * Variable que almacena el Optional de la anotación {@link Deprecated} en
     * caso de que exista su definición. Una {@link IRule} o {@link IAction} anotada
     * como deprecated significa que será removida en próximas versiones, por lo
     * tanto, se deberá de indicar la nueva {@link IRule} o {@link IAction} que se
     * será utilizada en su lugar.
     */
    @Field("deprecated")
    private boolean deprecated;

    /**
     * Lista de argumentos que se le pueden pasar a una acción para procesarla
     */
    @Field("arguments")
    private List<Argument> arguments = new ArrayList<>();
}
