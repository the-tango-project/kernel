package org.apeiron.kernel.service.exception;

import org.apeiron.kernel.web.rest.errors.ExceptionTranslator;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * Excepción usada cuando el argumento enviado es inválido.
 * <p>
 * Será traducida por el {@link ExceptionTranslator} a
 * un {@link Problem} del tipo {@link Status#BAD_REQUEST}.
 *
 * @see Problem
 * @see Status
 * @see ExceptionTranslator
 */
public class InvalidArgumentException extends ApeironApiException {

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
