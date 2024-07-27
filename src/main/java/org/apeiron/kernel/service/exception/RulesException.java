package org.apeiron.kernel.service.exception;

import java.util.List;
import org.apeiron.kernel.service.dto.RuleDto;
import org.apeiron.kernel.web.rest.errors.ExceptionTranslator;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

/**
 * Excepción usada cuando occurre un error de formalización.
 * <p>
 * Será traducida por el {@link ExceptionTranslator} a
 * un {@link Problem} del tipo {@link Status#INTERNAL_SERVER_ERROR}.
 *
 * @see Problem
 * @see Status
 * @see ExceptionTranslator
 */
public class RulesException extends ApeironApiException {

    private final List<RuleDto> errores;

    public RulesException(String message, List<RuleDto> errores) {
        super(message);
        this.errores = errores;
    }

    public List<RuleDto> getErrores() {
        return this.errores;
    }
}
