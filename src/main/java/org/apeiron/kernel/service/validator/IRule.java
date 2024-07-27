package org.apeiron.kernel.service.validator;

import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.transition.TransitionElement;
import reactor.core.publisher.Mono;

/**
 * La interfaz IRule es un elemento de transición empleado en el proceso de
 * transición de estado. El IRule es la intefaz que define una regla de
 * valiadición que se debe de cumplir antes de relizar un cambio de estado y
 * debe de ejecutarse durante el ciclo de validación del ápeiron, antes de la
 * ejecución de las acciones
 *
 * {@link org.apeiron.kernel.service.transition.TransitionElement}
 *
 *
 */
public interface IRule extends TransitionElement {
    /**
     * Valida que el objeto {@link Solicitud} cumpla con la regla definida en esta
     * implementación antes de llevar a cabo un cambio de transición
     *
     * @param context contexto para validar la regla
     * @return True si cumple con la regla, de otro modo False
     */
    public Mono<Boolean> validate(ContextDto context);
}
