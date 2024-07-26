package org.apeiron.kernel.commons.annotations;

import static java.util.Optional.ofNullable;
import static org.apeiron.kernel.utils.StringUtils.toCamelCase;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.ActionDTO;
import org.apeiron.kernel.service.dto.ArgumentDTO;
import org.apeiron.kernel.service.dto.RuleDTO;
import org.apeiron.kernel.service.validator.IRule;

/**
 * Clase encargada de leer y procesar las anotaciones de {@link ApeironAction},
 * {@link ApeironRule}, {@link Condition}, {@link Tags},
 * {@link java.lang.deprecated}
 *
 * Las anotaciones son procesadas y convertidas a objectos de tipo
 * {@link RuleDTO}, {@link ActionDTO}
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
     * construir el objeto {@link RuleDTO}
     *
     * @param ruleProvider regla a partir de la cual se procesarán las anotaciones
     * @return RuleDTO resuelta
     */
    public static RuleDTO resolveRule(IRule ruleProvider) {
        Class<?> clazz = ruleProvider.getClass();
        Method validateMethod = clazz.getMethods()[0];
        return toRuleDTO(
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
     * {@link ActionDTO}
     *
     * @param actionProvider acción a partir de la cual se leen las anotaciones
     * @return ActionDTO resuelta
     */
    public static ActionDTO resolveActions(IAction actionProvider) {
        Class<?> clazz = actionProvider.getClass();
        Method executeMethod = clazz.getMethods()[0];
        return toActionDTO(
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

    private static RuleDTO toRuleDTO(AnnotationContext context) {
        RuleDTO target = new RuleDTO();
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

    private static ActionDTO toActionDTO(ActionAnnotationContext context) {
        ActionDTO target = new ActionDTO();
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

    private static ArgumentDTO[] resolveArguments(Configurable context) {
        if (context.getArguments().isPresent()) {
            return AnnotationProcessor.processManyArguments(context.getArguments().get());
        }

        if (context.getArgument().isPresent()) {
            return AnnotationProcessor.processOneArgument(context.getArgument().get());
        }
        return new ArgumentDTO[0];
    }

    private static ArgumentDTO[] processManyArguments(Arguments arguments) {
        return Arrays.stream(arguments.value()).map(AnnotationProcessor::mapToArgumentDto).toArray(ArgumentDTO[]::new);
    }

    private static ArgumentDTO[] processOneArgument(Argument argument) {
        return new ArgumentDTO[] { AnnotationProcessor.mapToArgumentDto(argument) };
    }

    private static ArgumentDTO mapToArgumentDto(Argument argument) {
        return ArgumentDTO.builder().name(argument.name()).type(argument.type()).build();
    }
}
