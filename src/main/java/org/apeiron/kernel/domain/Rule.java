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
 * una Regla
 *
 */
@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Rule implements Serializable {

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
     * La condición es un texto que describe la condición a
     * partir de la cual la regla que se está definiendo es valida
     */
    @Field("condicion")
    private String condicion;

    /**
     * Variable que indica si la regla se encuentra deprecated o no
     */
    @Field("deprecated")
    private boolean deprecated;

    /**
     * Lista de argumentos que se le pueden pasar a una regla para su evaluación
     */
    @Field("arguments")
    private List<Argument> arguments = new ArrayList<>();

    /**
     * Lista de etiquetas para la regla
     */
    @Field("tags")
    private List<String> tags = new ArrayList<>();
}
