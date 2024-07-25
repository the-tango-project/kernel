package org.apeiron.kernel.utils;

import static java.util.Objects.isNull;

/**
 * Clase con métodos para dar formato a cadenas
 */
public class StringUtils {

    private StringUtils() {}

    /**
     * Método que regresa una cade de texto en formato Camel Case. Cuando el
     * parámetro from es null se regresa una cadena vacía
     *
     * @param from cadena de texto a la que se le dará formato
     * @return La cadena de texto en formato CamelCase
     */
    public static String toCamelCase(String from) {
        if (isNull(from)) {
            return "";
        }
        char[] c = from.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }
}
