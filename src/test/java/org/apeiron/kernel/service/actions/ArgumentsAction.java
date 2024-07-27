package org.apeiron.kernel.service.actions;

import org.apeiron.kernel.commons.annotations.ApeironAction;
import org.apeiron.kernel.commons.annotations.Argument;
import org.apeiron.kernel.commons.annotations.ArgumentType;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.ContextDto;
import reactor.core.publisher.Mono;

@ApeironAction(nombre = "Acción para probar argumentos dentro de acciones")
public class ArgumentsAction implements IAction {

    @Override
    @Argument(name = "dateTimeArgument", type = ArgumentType.DATE_TIME)
    @Argument(name = "dateArgument", type = ArgumentType.DATE)
    @Argument(name = "integerArgument", type = ArgumentType.INTEGER)
    @Argument(name = "floatArgument", type = ArgumentType.FLOAT)
    @Argument(name = "stringArgument", type = ArgumentType.STRING)
    @Argument(name = "propertyMapArgument", type = ArgumentType.PROPERTY_MAP)
    public Mono<Void> execute(ContextDto context) {
        return Mono.empty().then();
    }
}
