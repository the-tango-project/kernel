package org.apeiron.kernel.commons.annotations;

import java.util.Optional;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.validator.IRule;

/**
 * Interfaz que define los principales métodos que hacen a un
 * objeto configurable para Áperion. Un objecto Configurable puede ser definido
 * a través de las annotaciones {@link Argument} y {@link Arguments}. Un
 * elemento configurable define los parámetros que requiere para llevar a cabo
 * su tarea. En ápeiron, un objeto configurable, a partir del tipo de dato que
 * tenga {@link ArgumentType}, tiene su correspondiente elemento visual que le
 * permitirá al configurador de soluciones ingresar los parámetros que se
 * necesitan
 *
 * @see IRule
 * @see IAction
 *
 */
public interface Configurable {
    /**
     * Regresa un optional de la anotación Arguments {@link Arguments}
     *
     * @return {@link Arguments} envuelto en un optional
     */
    Optional<Arguments> getArguments();

    /**
     * Regresa un optional de la anotación Argument {@link Argument}
     *
     * @return {@link Argument} envuelto en un optional
     */
    Optional<Argument> getArgument();
}
