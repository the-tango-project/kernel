package org.apeiron.kernel.service.util;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

public class ErrorHelper {

    private static final Logger log = LoggerFactory.getLogger(ErrorHelper.class);

    private ErrorHelper() {}

    public static void logErrorHandling(Object ex, String cvu, String id) {
        int code = 0;
        if (ex instanceof FeignException) {
            code = ((FeignException) ex).status();
        } else if (ex instanceof NoFallbackAvailableException) {
            NoFallbackAvailableException exception = (NoFallbackAvailableException) ex;
            if (
                exception.getCause() != null &&
                exception.getCause().getCause() != null &&
                exception.getCause().getCause() instanceof FeignException
            ) {
                code = ((FeignException) exception.getCause().getCause()).status();
            }
        }

        if (code == 400) {
            log.error("Datos no válidos para el envió de ayudante a expediente, solicitud: {}", id);
        } else if (code == 404) {
            log.error("No se encontró el CVU: {}, solicitud: {}", cvu, id);
        } else if (code == 409) {
            log.error("Ocurrió un conflicto al enviar a expedientes, solicitud: {}", id);
        } else {
            log.error("Error al agregar ayudante en histórico, solicitud: {}, error: {}", id, ex);
        }
    }
}
