package org.apeiron.kernel.service.exception;

import org.apeiron.kernel.web.rest.errors.ExceptionTranslator;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * Excepciones lanzadas cuando existe un conflicto al realizar una operación
 * <p>
 * Si no se agrega una regla explícita en el {@link ExceptionTranslator},
 * será traducida a un {@link Problem} del tipo
 * {@link Status.CONFLICT}.
 *
 * @see Problem
 * @see Status
 * @see ExceptionTranslator
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
