package org.apeiron.kernel.service.rules;

import lombok.RequiredArgsConstructor;

import org.apeiron.kernel.autoconfiguration.Constants.RuleTag;
import org.apeiron.kernel.commons.annotations.ApeironRule;
import org.apeiron.kernel.commons.annotations.Argument;
import org.apeiron.kernel.commons.annotations.ArgumentType;
import org.apeiron.kernel.commons.annotations.Condition;
import org.apeiron.kernel.commons.annotations.Tags;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.validator.IRule;
import reactor.core.publisher.Mono;

@ApeironRule(nombre = "Regla para probar argumentos")
@Condition("Regla empleada para probar la lectura de argumentos dentro de una regla")
@Tags({ RuleTag.Must.VALIDACION_FECHA })
@RequiredArgsConstructor
public class ArgumentsRule implements IRule {

    @Override
    @Argument(name = "dateTimeArgument", type = ArgumentType.DATE_TIME)
    @Argument(name = "dateArgument", type = ArgumentType.DATE)
    @Argument(name = "integerArgument", type = ArgumentType.INTEGER)
    @Argument(name = "floatArgument", type = ArgumentType.FLOAT)
    @Argument(name = "stringArgument", type = ArgumentType.STRING)
    @Argument(name = "propertyMapArgument", type = ArgumentType.PROPERTY_MAP)
    public Mono<Boolean> validate(ContextDto context) {
        return Mono.just(true);
    }
}
