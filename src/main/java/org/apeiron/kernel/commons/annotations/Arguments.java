package org.apeiron.kernel.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apeiron.kernel.service.transition.TransitionElement;

/**
 * Anotación que envuelve un arreglo de {@link Argument}. Esta anotación es
 * utilizada para que se pueda repetir la anotación Argument dentro de un
 * {@link TransitionElement}
 *
 * @see Argument
 * @author Daniel Cortes Pichardo
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Arguments {
    /**
     * Regresa un arreglo de objetos de tipo {@link Argument} definidos dentro un
     * {@link TransitionElement}
     *
     * @return Arreglo de argumentos
     */
    Argument[] value();
}
