package org.apeiron.kernel.service.dto.factories;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;
import static org.apeiron.kernel.autoconfiguration.Constants.Html.STRONG_CLOSE;
import static org.apeiron.kernel.autoconfiguration.Constants.Html.STRONG_OPEN;
import static org.apeiron.kernel.autoconfiguration.Constants.TextFormat.DOT;
import static org.apeiron.kernel.autoconfiguration.Constants.TextFormat.SPACE;

import java.util.Arrays;
import java.util.List;
import org.apeiron.kernel.domain.enumeration.TipoAccion;
import org.apeiron.kernel.service.dto.RuleDto;

/**
 * Fabrica de objetos para la clase RuleDto y Rule
 */
public class RuleFactory {

    private RuleFactory() {}

    /**
     * Método que regresa la regla invalidTransitionByAction envuelta en una Lista,
     * de tal manera que se pueda utilizar en el framework de errores que emplea el
     * ápeiron
     *
     * @param action
     * @return la lista de objectos RuleDto
     */
    public static List<RuleDto> invalidTransitionByActionAsList(TipoAccion action) {
        return Arrays.asList(RuleFactory.invalidTransitionByAction(action));
    }

    /**
     * Método que crea una instancia del objeto RuleDto con la regla de negocio que
     * valida que una acción tenga su correspondiente transición. Cuando una acción
     * no tiene su correspondiente transición se debe de mostrar el mensaje que se
     * define este método
     *
     * @param action
     * @return RuleDto Regresa la regla invalidTransitionByAction
     *
     */
    public static RuleDto invalidTransitionByAction(TipoAccion action) {
        return RuleDto
            .builder()
            .clave("invalidTransitionByAction")
            .nombre("Aviso")
            .condicion(RuleFactory.invalidTransitionByActionMessage(action))
            .build();
    }

    private static String invalidTransitionByActionMessage(TipoAccion action) {
        return new StringBuilder()
            .append("Se ha ejecutado la acción")
            .append(SPACE)
            .append(STRONG_OPEN)
            .append(firstNonNull(action, "indefinida"))
            .append(STRONG_CLOSE)
            .append(DOT)
            .append(SPACE)
            .append("Por favor, espere los siguientes pasos")
            .toString();
    }
}
