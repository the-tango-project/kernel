package org.apeiron.kernel.service.exception;

import java.util.Arrays;
import org.apeiron.kernel.service.dto.RuleDto;

/**
 * Clase encargada de crear objetos del tip {@link RulesException}
 */
public class RuleExceptionFactory {

    private RuleExceptionFactory() {}

    public static RulesException cvuNotFound() {
        return new RulesException(
            "Registro de usuario",
            Arrays.asList(RuleDto.builder().condicion("El usuario con el cvu ingresado no existe").build())
        );
    }

    public static RulesException alreadyExist() {
        return new RulesException(
            "Ya existe un registro",
            Arrays.asList(RuleDto.builder().condicion("El usuario ya se encuentra registrado").build())
        );
    }
}
