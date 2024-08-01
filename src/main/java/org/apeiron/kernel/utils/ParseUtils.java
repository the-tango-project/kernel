package org.apeiron.kernel.utils;

import static java.util.Objects.isNull;
import static org.apeiron.kernel.configuration.Constants.Apeiron.DATE_FORMATTER;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase útil para analizar gramaticalmente (parse) cadenas de texto y
 * convertirlas a objetos conocidos. Principalmente, esta clase analiza
 * gramaticalmente texto que puede ser convertido a objetos de tipo LocalData,
 * Instant, Integer y Float
 */
@Slf4j
public class ParseUtils {

    private ParseUtils() {}

    /**
     * Obtiene un objeto de tipo LocalData a partir de una cadena de texto,
     * principalmente se utiliza el método
     * {@link java.time.LocalDate#parse(CharSequence)}
     *
     * En caso de que no sea posible obtener un objeto de tipo LocalDate, este
     * método regresa null
     *
     * @param dateString
     * @return objeto LocalDate
     */
    public static LocalDate parseLocalDateOrNull(String dateString) {
        if (isNull(dateString)) {
            return null;
        }

        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            log.error("No fue posible convertir el valor {} a LocalDate", dateString);
            return null;
        }
    }

    /**
     * Obtiene un objeto de tipo Instant a partir de una cadena de texto,
     * principalmente se utiliza el método
     * {@link java.time.Instant#parse(CharSequence)}
     *
     * En caso de que no sea posible obtener un objeto de tipo Instant, este
     * método regresa null
     *
     * @param valueAsString
     * @return Instant la fecha
     */
    public static Instant parseInstantOrNull(String valueAsString) {
        if (isNull(valueAsString)) {
            return null;
        }

        try {
            return Instant.parse(valueAsString);
        } catch (DateTimeParseException e) {
            log.error("No fue posible convertir el valor {} a Instant", valueAsString);
            return null;
        }
    }

    /**
     * Obtiene un objeto de tipo Integer a partir de una cadena de texto,
     * principalmente se utiliza el método
     * {@link java.lang.Integer#parseInt(String)}
     *
     * En caso de que no sea posible obtener un objeto de tipo Integer, este
     * método regresa null
     *
     * @param valueAsString
     * @return Integer el entero
     */
    public static Integer parseIntegerOrNull(String valueAsString) {
        if (isNull(valueAsString)) {
            return null;
        }

        try {
            return Integer.parseInt(valueAsString);
        } catch (NumberFormatException e) {
            log.error("No fue posible convertir el valor {} a Integer", valueAsString);
            return null;
        }
    }

    /**
     * Obtiene un objeto de tipo Float a partir de una cadena de texto,
     * principalmente se utiliza el método
     * {@link java.lang.Float#parseFloat(String)}
     *
     * En caso de que no sea posible obtener un objeto de tipo Float, este
     * método regresa null
     *
     * @param valueAsString
     * @return Float el Float
     */
    public static Float parseFloatOrNull(String valueAsString) {
        if (isNull(valueAsString)) {
            return null;
        }

        try {
            return Float.parseFloat(valueAsString);
        } catch (NumberFormatException e) {
            log.error("No fue posible convertir el valor {} a Float", valueAsString);
            return null;
        }
    }
}
