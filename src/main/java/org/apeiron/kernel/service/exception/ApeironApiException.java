package org.apeiron.kernel.service.exception;

import org.apeiron.kernel.web.rest.errors.ExceptionTranslator;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * Excepciones lanzadas por el microservicio.
 * <p>
 * Si no se agrega una regla explícita en el {@link ExceptionTranslator},
 * será traducida a un {@link Problem} del tipo
 * {@link Status#INTERNAL_SERVER_ERROR}.
 *
 * @see Problem
 * @see Status
 * @see ExceptionTranslator
 */
public abstract class ApeironApiException extends RuntimeException {

    /**
     * This code snippet is defining a constructor for the `ApeironApiException`
     * class in Java. The constructor takes a `String` parameter `message` and calls
     * the superclass
     * constructor of `RuntimeException` with that message. This means that when an
     * instance of
     * `ApeironApiException` is created with a message, that message will be passed
     * to the superclass
     * `RuntimeException` constructor for handling.
     * 
     * @param message
     */
    protected ApeironApiException(String message) {
        super(message);
    }

    /**
     * The code snippet you provided is defining a constructor for the
     * `ApeironApiException` class in Java
     * that takes a `Throwable` parameter `cause`. This constructor calls the
     * superclass constructor of
     * `RuntimeException` with the `cause` parameter.
     * 
     * @param cause
     */
    protected ApeironApiException(Throwable cause) {
        super(cause);
    }

    /**
     * This code snippet is defining a constructor for the `ApeironApiException`
     * class in Java that
     * takes two parameters: a `String` message and a `Throwable` cause. Inside
     * the constructor, it
     * calls the superclass constructor of `RuntimeException` with these two
     * parameters.
     * 
     * @param message
     * @param cause
     */
    protected ApeironApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * This constructor for the `ApeironApiException` class in Java takes four
     * parameters: a `String`
     * message, a `Throwable` cause, a boolean `enableSuppression`, and a boolean
     * `writableStackTrace`. It
     * then calls the superclass constructor of `RuntimeException` with these
     * four
     * parameters.
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    protected ApeironApiException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
