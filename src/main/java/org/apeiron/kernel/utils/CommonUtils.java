package org.apeiron.kernel.utils;

import java.util.Random;

/**
 * Clase útil con métodos genéricos que se utilizan en varias parte del código.
 */
public class CommonUtils {

    private static Random random = new Random();

    private CommonUtils() {}

    /**
     * Regresa un valor aleatorio entre 0 y 1000
     *
     * @return int
     */
    public static int getRandomInt() {
        return random.nextInt(1000);
    }

    /**
     * Construye el id de la solicitud de vigencia para expediente
     *
     * @param idSolicitud id de la solicitud de apeiron
     * @param claveNivelSni clave del nivel sni del investigador
     * @param claveTipo clave del tipo de vigencia
     * @return String id de la solicitud de vigencia
     */
    public static String buildIdSolicitudVigencia(String idSolicitud, String claveNivelSni, String claveTipo) {
        final char separator = '_';
        return new StringBuilder(idSolicitud).append(separator).append(claveNivelSni).append(separator).append(claveTipo).toString();
    }

    /**
     * Valida el formato del id de la solicitud de vigencia a partir de la estructura básica
     * Si tiene tres partes separadas por guión bajo se considera un formato válido
     * @param idSolicitudVigencia id de la solicitud de vigencia
     * @return boolean true si el formato es válido
     */
    public static boolean isValidIdSolicitudVigencia(String idSolicitudVigencia) {
        return idSolicitudVigencia.split("_").length == 3;
    }
}
