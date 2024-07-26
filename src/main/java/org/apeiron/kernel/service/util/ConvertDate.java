package org.apeiron.kernel.service.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ConvertDate {

    private static final DateTimeFormatter[] FORMATOS_FECHA = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'"),
        DateTimeFormatter.ofPattern("yyyy/MM/dd"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
        // Agrega más formatos de fecha según tus necesidades
    };

    private ConvertDate() {
        // Clase utilitaria, no se permite instanciar
    }

    /**
     * Parsea la fecha a un local date.
     *
     * @param fecha
     * @return
     */
    public static LocalDate convertirToLocalDate(String fecha) {
        if (fecha == null || fecha == "" || fecha.equals("") || fecha.isEmpty()) {
            return null;
        }

        LocalDate fechaDate = null;
        for (DateTimeFormatter formato : FORMATOS_FECHA) {
            try {
                fechaDate = LocalDate.parse(fecha, formato); // ToDo: Validar previamente el pattern
                break; // Si se encuentra un formato válido, se sale del bucle
            } catch (DateTimeParseException e) {
                log.trace("Error al realizar la conversión de fechas", e);
            }
        }
        return fechaDate;
    }
}
