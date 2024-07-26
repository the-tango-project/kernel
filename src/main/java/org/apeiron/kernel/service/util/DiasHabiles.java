package org.apeiron.kernel.service.util;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

/**
 * Clase utilitaria para calcular días hábiles
 */
public final class DiasHabiles {

    private DiasHabiles() {
        // Clase utilitaria, no se permite instanciar
    }

    /**
     * Calcula la fecha de acuerdo a los días hábiles
     * @param dias Número de días hábiles a calcular
     * @param fechaInicial Fecha inicial para el cálculo
     * @param posterior Indica si el calcula se hace hacia atrás o hacia adelante
     * @return La fecha calculada
     */
    public static ZonedDateTime calcular(int dias, ZonedDateTime fechaInicial, boolean posterior) {
        int conta = 0;

        while (conta < dias) {
            if (
                fechaInicial.getDayOfWeek() != DayOfWeek.SATURDAY &&
                fechaInicial.getDayOfWeek() != DayOfWeek.SUNDAY &&
                !MexicoHolidays.isHoliday(fechaInicial.toLocalDate())
            ) {
                conta++;
            }
            fechaInicial = posterior ? fechaInicial.plusDays(1) : fechaInicial.minusDays(1);
        }

        return fechaInicial;
    }
}
