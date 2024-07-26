package org.apeiron.kernel.service.exception;

import org.apeiron.kernel.web.rest.errors.ExceptionTranslator;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * Excepciones lanzadas por el microservicio.
 * <p>
 * Si no se agrega una regla explícita en el {@link ExceptionTranslator},
 * será traducida a un {@link Problem} del tipo {@link Status#INTERNAL_SERVER_ERROR}.
 *
 * @see Problem
 * @see Status
 * @see ExceptionTranslator
 */
public abstract class ApeironApiException extends RuntimeException {

    protected ApeironApiException(String message) {
        super(message);
    }

    protected ApeironApiException(Throwable cause) {
        super(cause);
    }

    protected ApeironApiException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ApeironApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
