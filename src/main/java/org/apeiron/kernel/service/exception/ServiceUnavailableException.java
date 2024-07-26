package org.apeiron.kernel.service.exception;

import org.apeiron.kernel.web.rest.errors.ExceptionTranslator;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * Excepción usada cuando el servicio no esta disponible.
 * <p>
 * Será traducida por el {@link ExceptionTranslator} a
 * un {@link Problem} del tipo {@link Status#NOT_FOUND}.
 *
 * @see Problem
 * @see Status
 * @see ExceptionTranslator
 */
public class ServiceUnavailableException extends ApeironApiException {

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(Throwable cause) {
        super(cause);
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceUnavailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
