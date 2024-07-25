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
}
