package org.apeiron.kernel.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apeiron.kernel.service.transition.TransitionElement;

/**
 * Anotación Argument que es empleada para definir un argumento, y su tipo
 * {@link ArgumentType}. Los argumentos son parámetros que se le pueden pasar a
 * un {@link TransitionElement} para ejecutar su tarea.
 *
 * @author Daniel Cortes Pichardo
 *
 */
@Repeatable(Arguments.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Argument {
    /**
     * Regresa el nombre del argumento
     *
     * @return nombre del argumento
     */
    String name();

    /**
     * regresa el tipo del argumento. Como valor predeterminado se dice que un
     * argumento es de tipo {@link ArgumentType#STRING}
     *
     * @return El tipo de argumento
     */
    ArgumentType type() default ArgumentType.STRING;
}
