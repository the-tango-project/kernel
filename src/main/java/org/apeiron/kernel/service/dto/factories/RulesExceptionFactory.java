package org.apeiron.kernel.service.dto.factories;

import org.apeiron.kernel.domain.enumeration.TipoAccion;
import org.apeiron.kernel.service.exception.RulesException;

public class RulesExceptionFactory {

    private RulesExceptionFactory() {}

    public static RulesException invalidTransitionByAction(TipoAccion accion) {
        return new RulesException("solucionId", RuleFactory.invalidTransitionByActionAsList(accion));
    }
}
