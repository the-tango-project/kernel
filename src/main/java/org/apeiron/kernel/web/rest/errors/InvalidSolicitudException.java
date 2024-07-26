package org.apeiron.kernel.web.rest.errors;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apeiron.kernel.service.dto.RuleDTO;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class InvalidSolicitudException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    private final String entityName;

    private final String errorKey;

    private final List<RuleDTO> errores;

    public InvalidSolicitudException(String defaultMessage, String errorKey, List<RuleDTO> errores) {
        this(ErrorConstants.DEFAULT_TYPE, defaultMessage, "solicitud", errorKey, errores);
    }

    public InvalidSolicitudException(URI type, String defaultMessage, String entityName, String errorKey, List<RuleDTO> errores) {
        super(type, defaultMessage, Status.PRECONDITION_FAILED, null, null, null, getAlertParameters(entityName, errorKey));
        this.entityName = entityName;
        this.errorKey = errorKey;
        this.errores = errores;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public List<RuleDTO> getErrores() {
        return errores;
    }

    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }
}
