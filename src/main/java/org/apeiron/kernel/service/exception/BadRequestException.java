package org.apeiron.kernel.service.exception;

import org.apeiron.kernel.web.rest.errors.ExceptionTranslator;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * Excepción utilizada para solicitudes no validas.
 * <p>
 * Será traducida por el {@link ExceptionTranslator} a
 * un {@link Problem} del tipo {@link Status#BAD_REQUEST}.
 *
 * @see Problem
 * @see Status
 * @see ExceptionTranslator
 */
public class BadRequestException extends ApeironApiException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
