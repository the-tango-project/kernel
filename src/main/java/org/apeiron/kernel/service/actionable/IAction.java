package org.apeiron.kernel.service.actionable;

import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.service.dto.ContextDTO;
import org.apeiron.kernel.service.transition.TransitionElement;
import reactor.core.publisher.Mono;

/**
 * La interfaz IAction es un elemento de transición empleado en el proceso de
 * transición de estado. El IAction es la intefaz que define una acción que se
 * debe de ejecutar después del ciclo de validación del ápeiron.
 *
 * {@link org.apeiron.kernel.service.transition.TransitionElement}
 *
 */
public interface IAction extends TransitionElement {
    /**
     * Método que se ejecuta después del ciclo de validaciónes del ápeiron, de tal
     * manera que se puedan llevar a cabo acciones una vez que el objeto
     * {@link Solicitud} se encuentra validado
     *
     * @param context para ejecutar una acción
     * @return Mono<Void>
     */
    Mono<Void> execute(ContextDTO context);
}
