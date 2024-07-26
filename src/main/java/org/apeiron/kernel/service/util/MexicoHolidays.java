package org.apeiron.kernel.service.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase utilitaria para el manejo de días festivos en México.
 */
public final class MexicoHolidays {

    private static final LocalDate FECHA_ACTUAL = LocalDate.now();

    protected static final Set<LocalDate> HOLIDAYS = new HashSet<>(
        Arrays.asList(
            // Días festivos oficiales de México

            LocalDate.of(FECHA_ACTUAL.getYear(), Month.JANUARY, 1), // Año Nuevo
            LocalDate.of(FECHA_ACTUAL.getYear(), Month.FEBRUARY, 6), // Día de la Constitución
            LocalDate.of(FECHA_ACTUAL.getYear(), Month.MARCH, 20), // Natalicio de Benito Juárez
            LocalDate.of(FECHA_ACTUAL.getYear(), Month.MAY, 1), // Día del Trabajo
            LocalDate.of(FECHA_ACTUAL.getYear(), Month.SEPTEMBER, 16), // Día de la Independencia
            LocalDate.of(FECHA_ACTUAL.getYear(), Month.NOVEMBER, 20), // Revolución Mexicana
            LocalDate.of(FECHA_ACTUAL.getYear(), Month.DECEMBER, 25) // Navidad
        )
    );

    private MexicoHolidays() {
        // Clase utilitaria, no se permite instanciar
    }

    public static boolean isHoliday(LocalDate date) {
        return HOLIDAYS.contains(date);
    }
}
