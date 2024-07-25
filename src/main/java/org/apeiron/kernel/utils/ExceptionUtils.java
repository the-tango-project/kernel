package org.apeiron.kernel.utils;

import feign.FeignException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Clase utilería para manejo de excepciones cómo FeignException.
 * <p>
 * En dónde se repite el código de verificación de status code.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionUtils {

    /**
     * Verifica si la excepción es de tipo FeignException y el status code es 404.
     * <p>
     * Si es así regresa true es decir no se encontró el recurso, de lo contrario false.
     * @param throwable excepción a verificar
     * @return true si es de tipo FeignException y el status code es 404, false de lo contrario.
     */
    public static Boolean notFound(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause.getCause() instanceof FeignException) {
            FeignException feignException = (FeignException) cause.getCause();
            if (feignException.status() == HttpStatus.NOT_FOUND.value()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
