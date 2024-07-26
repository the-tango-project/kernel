package org.apeiron.kernel.service.validator;

import jakarta.validation.constraints.NotNull;
import org.apeiron.kernel.service.dto.ContextDTO;
import reactor.core.publisher.Mono;

/**
 * Interface Validator que debe se implementada por una clase concreta que
 * lleve a cabo la validación del objetio de Solicitud a través del flujo de la
 * transición del ápeiron. Esta inteface es el mecanismo empleado para validar
 * que una transición se lleva a cabo de manera correcta a partir de las reglas
 * definidas en una solución.
 *
 */
public interface Validator {
    /**
     * Valida una solicitud a partir del contexto que se le comparte. Este método es
     * el encargado de llamar a todas las clases que implementan la interface IRule
     * con la finalidad de validar el objeto de solicitud antes de llevar a cabo una
     * transición. Como resultado exitoso, se puede proceder al cambio de transición
     * y ejecución de postactions
     *
     * @param contexto
     * @return Mono<Result> resultado del proceso de validación
     */
    public Mono<Result> validate(@NotNull ContextDTO contexto);

    /**
     * Lleva a cabo la misma tarea que el método validate() pero de manera parcial y
     * acotada al formulario que se está procesando. El resultado de este método no
     * debe ocasionar ninguna transición, ya que se trata de una validación parcial
     * y sólo aplicada para un formulario
     *
     * @param contexto
     * @return Mono<Result> resultado del proceso de validación
     */
    public Mono<Result> validateForm(@NotNull ContextDTO contexto);
}
