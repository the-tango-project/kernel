package org.apeiron.kernel.commons.annotations;

import static java.util.Optional.ofNullable;
import static org.apeiron.kernel.utils.StringUtils.toCamelCase;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.ActionDto;
import org.apeiron.kernel.service.dto.ArgumentDto;
import org.apeiron.kernel.service.dto.RuleDto;
import org.apeiron.kernel.service.validator.IRule;

/**
 * Clase encargada de leer y procesar las anotaciones de {@link ApeironAction},
 * {@link ApeironRule}, {@link Condition}, {@link Tags},
 * {@link java.lang.Deprecated}
 *
 * Las anotaciones son procesadas y convertidas a objectos de tipo
 * {@link RuleDto}, {@link ActionDto}
 *
 * El resultado de este procesamiento, se utiliza para visualizar reglas y
 * acciones en el configurador de reglas y acciones del áperion, de tal modo que
 * las reglas y acciones sean configurables en el proceso general del ápeiron
 */
public class AnnotationProcessor {

    private AnnotationProcessor() {}

    /**
     * Método que, a partir de una clase que implementa la interfaz {@link IRule}
     * lee las anotaciones {@link ApeironRule}, {@link Condition},
     * {@link Tags}, {@link Deprecated}, {@link Arguments} y {@link Argument} para
     * construir el objeto {@link RuleDto}
     *
     * @param ruleProvider regla a partir de la cual se procesarán las anotaciones
     * @return RuleDto resuelta
     */
    public static RuleDto resolveRule(IRule ruleProvider) {
        Class<?> clazz = ruleProvider.getClass();
        Method validateMethod = clazz.getMethods()[0];
        return toRuleDto(
            AnnotationContext
                .builder()
                .clave(toCamelCase(clazz.getSimpleName()))
                .rule(ofNullable(clazz.getAnnotation(ApeironRule.class)))
                .condition(ofNullable(clazz.getAnnotation(Condition.class)))
                .tags(ofNullable(clazz.getAnnotation(Tags.class)))
                .deprecated(ofNullable(clazz.getAnnotation(Deprecated.class)))
                .arguments(ofNullable(validateMethod.getAnnotation(Arguments.class)))
                .argument(ofNullable(validateMethod.getAnnotation(Argument.class)))
                .build()
        );
    }

    /**
     * Método que, a partir de una clase que implementa la interfaz {@link IAction}
     * lee las anotaciones {@link ApeironAction}, {@link Deprecated},
     * {@link Arguments} y {@link Argument} para construir el objeto
     * {@link ActionDto}
     *
     * @param actionProvider acción a partir de la cual se leen las anotaciones
     * @return ActionDto resuelta
     */
    public static ActionDto resolveActions(IAction actionProvider) {
        Class<?> clazz = actionProvider.getClass();
        Method executeMethod = clazz.getMethods()[0];
        return toActionDto(
            ActionAnnotationContext
                .builder()
                .clave(toCamelCase(clazz.getSimpleName()))
                .action(ofNullable(clazz.getAnnotation(ApeironAction.class)))
                .deprecated(ofNullable(clazz.getAnnotation(Deprecated.class)))
                .arguments(ofNullable(executeMethod.getAnnotation(Arguments.class)))
                .argument(ofNullable(executeMethod.getAnnotation(Argument.class)))
                .build()
        );
    }

    private static RuleDto toRuleDto(AnnotationContext context) {
        RuleDto target = new RuleDto();
        target.setClave(context.getClave());

        if (context.getRule().isPresent()) {
            target.setNombre(context.getRule().get().nombre());
        }
        if (context.getCondition().isPresent()) {
            target.setCondicion(context.getCondition().get().value());
        }
        if (context.getTags().isPresent()) {
            target.setTags(context.getTags().get().value());
        }
        if (context.getDeprecated().isPresent()) {
            target.setDeprecated(true);
        }

        if (AnnotationProcessor.hasArguments(context)) {
            target.setArguments(AnnotationProcessor.resolveArguments(context));
        }

        return target;
    }

    private static ActionDto toActionDto(ActionAnnotationContext context) {
        ActionDto target = new ActionDto();
        target.setClave(context.getClave());

        if (context.getAction().isPresent()) {
            target.setNombre(context.getAction().get().nombre());
        }

        if (context.getDeprecated().isPresent()) {
            target.setDeprecated(true);
        }

        if (AnnotationProcessor.hasArguments(context)) {
            target.setArguments(AnnotationProcessor.resolveArguments(context));
        }

        return target;
    }

    private static boolean hasArguments(Configurable context) {
        return context.getArguments().isPresent() || context.getArgument().isPresent();
    }

    private static ArgumentDto[] resolveArguments(Configurable context) {
        if (context.getArguments().isPresent()) {
            return AnnotationProcessor.processManyArguments(context.getArguments().get());
        }

        if (context.getArgument().isPresent()) {
            return AnnotationProcessor.processOneArgument(context.getArgument().get());
        }
        return new ArgumentDto[0];
    }

    private static ArgumentDto[] processManyArguments(Arguments arguments) {
        return Arrays.stream(arguments.value()).map(AnnotationProcessor::mapToArgumentDto).toArray(ArgumentDto[]::new);
    }

    private static ArgumentDto[] processOneArgument(Argument argument) {
        return new ArgumentDto[] { AnnotationProcessor.mapToArgumentDto(argument) };
    }

    private static ArgumentDto mapToArgumentDto(Argument argument) {
        return ArgumentDto.builder().name(argument.name()).type(argument.type()).build();
    }
}
