package org.apeiron.kernel.service.rules;

import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apeiron.kernel.autoconfiguration.Constants.RuleTag;
import org.apeiron.kernel.commons.annotations.ApeironRule;
import org.apeiron.kernel.commons.annotations.Argument;
import org.apeiron.kernel.commons.annotations.ArgumentType;
import org.apeiron.kernel.commons.annotations.Condition;
import org.apeiron.kernel.commons.annotations.Tags;
import org.apeiron.kernel.service.dto.ContextDTO;
import org.apeiron.kernel.service.util.ArgumentUtils;
import org.apeiron.kernel.service.validator.IRule;
import reactor.core.publisher.Mono;

@ApeironRule(nombre = "Operación fuera del horario permitido")
@Condition("La operación ejecutada se encuentra fuera del horario permitido")
@Tags({ RuleTag.Must.VALIDACION_FECHA })
@Slf4j
@RequiredArgsConstructor
public class ValidacionDeFechaRule implements IRule {

    private static final String FECHA_LIMITE_NAME = "fechaLimite";

    /**
     * Valida que se cumpla que la fecha y hora actual Instant.now() sean menor o
     * igual a la fecha y hora indicadas en la variable {@link #FECHA_LIMITE_NAME}
     *
     */
    @Override
    @Argument(name = FECHA_LIMITE_NAME, type = ArgumentType.DATE_TIME)
    public Mono<Boolean> validate(ContextDTO context) {
        Optional<Instant> fechaLimiteOptional = ArgumentUtils.asDateTime(context, this, FECHA_LIMITE_NAME);

        if (fechaLimiteOptional.isPresent()) {
            return Mono.just(Instant.now().isBefore(fechaLimiteOptional.get()));
        }
        log.error("No se pudo encontrar el parámetro {} en algún Argument", FECHA_LIMITE_NAME);
        return Mono.just(false);
    }
}
