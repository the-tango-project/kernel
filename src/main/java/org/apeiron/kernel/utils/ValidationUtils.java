package org.apeiron.kernel.utils;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import org.apeiron.kernel.config.Constants.AyudantesConstants;

public class ValidationUtils {

    private ValidationUtils() {}

    public static <T> boolean isNotEmpy(T element) {
        if (element instanceof Collection) {
            return isNotArrayEmpty((Collection<T>) element);
        } else if (element instanceof String) {
            return isStringNotEmpty((String) element);
        } else {
            return nonNull(element);
        }
    }

    public static <T> boolean isNotArrayEmpty(Collection<T> element) {
        return (nonNull(element) && !element.isEmpty());
    }

    public static <T> boolean isArrayEmpty(Collection<T> element) {
        return (isNull(element) || element.isEmpty());
    }

    public static boolean isStringNotEmpty(String string) {
        return (nonNull(string) && !string.trim().isEmpty());
    }

    /**
     * Dado el mapa de ayudante de una solicitud de tipo AYUDANTES regresa
     * la cantidad de UMAS asignadas al la solicitud
     * @param ayudante el mapa con la información del ayudante (se obtiene generalmente del
     * properties)
     * @return la cantidad de UMAS asignadas a ese ayudante en la solicitud.
     */
    public static Integer obtieneUmasAsignadasEnSolicitudAyudantes(Map<String, Object> ayudante) {
        var umas = ayudante != null ? ayudante.get(AyudantesConstants.UMA_KEY) : null;
        Integer umasAsignadas = 0;
        if (umas != null) umasAsignadas = (Integer) umas;
        return umasAsignadas;
    }

    /**
     * Dado el mapa de ayudante de una solicitud de tipo AYUDANTES regresa
     * la cantidad de UMAS ANTERIOR asignadas al la solicitud
     * @param ayudante el mapa con la información del ayudante (se obtiene generalmente del
     * properties)
     * @return la cantidad de UMAS ANTERIOR asignadas a ese ayudante en la solicitud.
     */
    public static Integer obtieneUmasAnteriorAsignadasEnSolicitudAyudantes(Map<String, Object> ayudante) {
        var umas = ayudante != null ? ayudante.get(AyudantesConstants.UMA_ANTERIOR_KEY) : null;
        Integer umasAsignadas = 0;
        if (umas != null) umasAsignadas = (Integer) umas;
        return umasAsignadas;
    }

    /**
     * Como hay problemas de guardado de fecha, se agrega el siguiente método
     * para hacer el parse por si es un String o un Date y/o una fecha retroactiva (porque cambia el formato)
     * @param fecha la fecha a  paresear (un Date o String en un formato ISO o un formato simple de ayudantes)
     * @param isRetroactiva un boolean para saber si se trata de una fecha retroactiva o no
     * @return un LocalDate con el valor de la fecha
     */
    public static LocalDate parseFecha(Object fecha, Boolean isRetroactiva) {
        if (Boolean.TRUE.equals(isRetroactiva) && fecha instanceof String) {
            return LocalDate.parse((String) fecha, AyudantesConstants.FORMATO_FECHA);
        }
        if (fecha instanceof String) {
            return LocalDate.parse((String) fecha, DateTimeFormatter.ISO_DATE_TIME);
        } else if (fecha instanceof Date) {
            return ((Date) fecha).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            throw new IllegalArgumentException("El objeto no es una fecha válida");
        }
    }

    /**
     * Como hay problemas de guardado de fecha, se agrega el siguiente método
     * para hacer el parse a OffsetDateTime por si es un String o un Date y/o una fecha retroactiva (porque cambia el formato)
     * @param fecha la fecha a paresear (un Date o String en un formato ISO o un formato simple de ayudantes)
     * @param isRetroactiva un boolean para saber si se trata de una fecha retroactiva o no
     * @return un OffsetDateTime con el valor de la fecha
     */
    public static OffsetDateTime parseOffsetDateTime(Object fecha, Boolean isRetroactiva) {
        if (Boolean.TRUE.equals(isRetroactiva) && fecha instanceof String) {
            return LocalDateTime.parse((String) fecha, AyudantesConstants.FORMATO_FECHA).atOffset(OffsetDateTime.now().getOffset());
        }
        if (fecha instanceof String) {
            return LocalDateTime
                .parse((String) fecha, DateTimeFormatter.ISO_DATE_TIME)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .atOffset(ZoneOffset.UTC);
        } else if (fecha instanceof Date) {
            return Instant.ofEpochMilli(((Date) fecha).getTime()).atOffset(ZoneOffset.UTC);
        } else {
            return null;
        }
    }
}
