package org.apeiron.kernel.service.util;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apeiron.kernel.utils.ParseUtils.parseFloatOrNull;
import static org.apeiron.kernel.utils.ParseUtils.parseInstantOrNull;
import static org.apeiron.kernel.utils.ParseUtils.parseIntegerOrNull;
import static org.apeiron.kernel.utils.ParseUtils.parseLocalDateOrNull;
import static org.apeiron.kernel.utils.StringUtils.toCamelCase;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.ArgumentDto;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.dto.Parametrizable;
import org.apeiron.kernel.service.dto.PropertyMapDto;
import org.apeiron.kernel.service.transition.TransitionElement;
import org.apeiron.kernel.service.validator.IRule;

/**
 * Clase útil con métodos que ayudan a recuperar parámetros del objeto
 * ContextDto, de tal manera que se puedan consultar los valores asignados
 * durante la configuración de una IRule o un IAction. Los parametros que se
 * intenten buscar deberán de estar definidos dentro de clases que implementen
 * las
 * interfaces de IRule o IAction
 *
 * {@link org.apeiron.kernel.service.actionable.IAction}
 * {@link org.apeiron.kernel.service.validator.IRule}
 *
 * Un parametro se define a través de las anotaciones de Argument y Arguments y
 * se les asigna un valor a través de la propiedad name
 *
 * {@link org.apeiron.kernel.commons.annotations.Argument#name()}
 * {@link org.apeiron.kernel.commons.annotations.Arguments}
 * {@link org.apeiron.kernel.commons.annotations.Argument}
 *
 *
 */
@Slf4j
public class ArgumentUtils {

    private ArgumentUtils() {}

    /**
     * Método que regresa el valor de un argumento como String envuelto en el objeto
     * Optional. En caso de que el parametro argumentName no se encuentre dentro del
     * objeto transitionElement y del context, entonces se regresa un
     * Optional.empty()
     *
     * @param context           contexto del flujo de validación y acciones
     * @param transitionElement elemento de transición
     * @param argumentName      nombre del argumento del que se quiere saber su
     *                          valor
     * @return Valor del argumento como Optional. Se regresa vacío en caso de que no
     *         exista
     */
    public static Optional<String> asString(ContextDto context, TransitionElement transitionElement, String argumentName) {
        ArgumentDto argument = resolveArgument(context, transitionElement, argumentName);
        return Optional.ofNullable(resolveString(getValue(argument)));
    }

    /**
     * Método que regresa el valor de un argumento como Integer envuelto en el
     * objeto Optional. En caso de que el parametro argumentName no se encuentre
     * dentro del objeto transitionElement y del context, entonces se regresa un
     * Optional.empty(). Así mismo, en caso de que el valor del argumento no se
     * pueda convertir a un Integer a través del método
     * {@link java.lang.Integer#parseInt(String)}, entonces se regresará siempre un
     * Optiona.empty().
     *
     * @param context           contexto del flujo de validación y acciones
     * @param transitionElement elemento de transición
     * @param argumentName      nombre del argumento del que se quiere saber su
     *                          valor
     * @return Valor del argumento como Optional. Se regresa vacío en caso de que no
     *         exista
     */
    public static Optional<Integer> asInteger(ContextDto context, TransitionElement transitionElement, String argumentName) {
        ArgumentDto argument = resolveArgument(context, transitionElement, argumentName);
        return Optional.ofNullable(resolveInteger(getValue(argument)));
    }

    /**
     * Método que regresa el valor de un argumento como Float envuelto en el
     * objeto Optional. En caso de que el parametro argumentName no se encuentre
     * dentro del objeto transitionElement y del context, entonces se regresa un
     * Optional.empty(). Así mismo, en caso de que el valor del argumento no se
     * pueda convertir a un Integer a través del método
     * {@link java.lang.Float#parseFloat(String)}, entonces se regresará siempre un
     * Optiona.empty().
     *
     * @param context           contexto del flujo de validación y acciones
     * @param transitionElement elemento de transición
     * @param argumentName      nombre del argumento del que se quiere saber su
     *                          valor
     * @return Valor del argumento como Optional. Se regresa vacío en caso de que no
     *         exista
     */
    public static Optional<Float> asFloat(ContextDto context, TransitionElement transitionElement, String argumentName) {
        ArgumentDto argument = resolveArgument(context, transitionElement, argumentName);
        return Optional.ofNullable(resolveFloat(getValue(argument)));
    }

    /**
     * Método que regresa el valor de un argumento como LocalDate envuelto en el
     * objeto Optional. En caso de que el parametro argumentName no se encuentre
     * dentro del objeto transitionElement y del context, entonces se regresa un
     * Optional.empty(). Así mismo, en caso de que el valor del argumento no se
     * pueda convertir a un Integer a través del método
     * {@link java.time.LocalDate#parse(CharSequence, java.time.format.DateTimeFormatter)},
     * entonces se regresará siempre un Optiona.empty().
     *
     * @param context           contexto del flujo de validación y acciones
     * @param transitionElement elemento de transición
     * @param argumentName      nombre del argumento del que se quiere saber su
     *                          valor
     * @return Valor del argumento como Optional. Se regresa vacío en caso de que no
     *         exista
     */
    public static Optional<LocalDate> asDate(ContextDto context, TransitionElement transitionElement, String argumentName) {
        ArgumentDto argument = resolveArgument(context, transitionElement, argumentName);
        return Optional.ofNullable(resolveLocalDate(getValue(argument)));
    }

    /**
     * Método que regresa el valor de un argumento como Instant envuelto en el
     * objeto Optional. En caso de que el parametro argumentName no se encuentre
     * dentro del objeto transitionElement y del context, entonces se regresa un
     * Optional.empty(). Así mismo, en caso de que el valor del argumento no se
     * pueda convertir a un Integer a través del método
     * {@link java.time.Instant#parse(CharSequence)}, entonces se regresará siempre
     * un Optiona.empty().
     *
     * @param context           contexto del flujo de validación y acciones
     * @param transitionElement elemento de transición
     * @param argumentName      nombre del argumento del que se quiere saber su
     *                          valor
     * @return Valor del argumento como Optional. Se regresa vacío en caso de que no
     *         exista
     */
    public static Optional<Instant> asDateTime(ContextDto context, TransitionElement transitionElement, String argumentName) {
        ArgumentDto argument = resolveArgument(context, transitionElement, argumentName);
        return Optional.ofNullable(resolveInstant(getValue(argument)));
    }

    /**
     * Método que regresa el valor de un argumento como PropertyMap envuelto en el
     * objeto Optional. En caso de que el parametro argumentName no se encuentre
     * dentro del objeto transitionElement y del context, entonces se regresa un
     * Optional.empty();
     *
     * @param context           contexto del flujo de validación y acciones
     * @param transitionElement elemento de transición
     * @param argumentName      nombre del argumento del que se quiere saber su
     *                          valor
     * @return Optional de PropertyMapValor. Se regresa vacío en caso de que no
     *         exista
     */
    public static Optional<List<PropertyMapDto>> asPropertyMap(
        ContextDto context,
        TransitionElement transitionElement,
        String argumentName
    ) {
        ArgumentDto argument = resolveArgument(context, transitionElement, argumentName);
        return Optional.ofNullable(resolvePropertyMap(getValue(argument)));
    }

    private static ArgumentDto resolveArgument(ContextDto context, TransitionElement transitionElement, String argumentName) {
        String clave = toCamelCase(transitionElement.getClass().getSimpleName());
        Parametrizable parametrizable = findParametrizable(resolveParametrizable(context, transitionElement), clave);
        log.debug("Se encontró el parametro con clave {} ", parametrizable.getClave());
        return findArgument(getArguments(parametrizable), argumentName);
    }

    private static <T> List<? extends Parametrizable> resolveParametrizable(ContextDto context, T transitionElement) {
        if (transitionElement instanceof IRule) {
            return context.getTransicion().getReglas();
        } else if (transitionElement instanceof IAction) {
            return context.getTransicion().getAcciones();
        }
        log.warn("El Transition Element no es un IRule o un IAction");
        return Collections.emptyList();
    }

    private static ArgumentDto[] getArguments(Parametrizable parametrizable) {
        if (nonNull(parametrizable)) {
            return parametrizable.getArguments();
        }

        log.warn("No se encontró un elemento parametrizable, por lo que se regresa un arreglo vacío");
        return new ArgumentDto[] {};
    }

    private static Object getValue(ArgumentDto argument) {
        if (nonNull(argument)) {
            return argument.getValue();
        }
        log.warn("No se encontró un argumento por lo que se regresa null como valor");
        return null;
    }

    private static Parametrizable findParametrizable(List<? extends Parametrizable> parametrizables, String clave) {
        return parametrizables.stream().filter(regla -> regla.getClave().equals(clave)).reduce((first, second) -> first).orElse(null);
    }

    private static ArgumentDto findArgument(ArgumentDto[] arguments, String argumentName) {
        return Arrays
            .stream(arguments)
            .filter(argument -> argument.getName().equals(argumentName))
            .reduce((first, second) -> first)
            .orElse(null);
    }

    private static LocalDate resolveLocalDate(Object object) {
        return parseLocalDateOrNull(resolveString(object));
    }

    private static Integer resolveInteger(Object object) {
        return parseIntegerOrNull(resolveString(object));
    }

    private static Float resolveFloat(Object object) {
        return parseFloatOrNull(resolveString(object));
    }

    private static Instant resolveInstant(Object object) {
        return parseInstantOrNull(resolveString(object));
    }

    private static String resolveString(Object object) {
        if (isNull(object)) {
            return null;
        }

        if (object instanceof String) {
            return (String) object;
        } else {
            return object.toString();
        }
    }

    private static List<PropertyMapDto> resolvePropertyMap(Object object) {
        if (isNull(object)) {
            return new ArrayList<>();
        }

        if (object instanceof List<?>) {
            List<PropertyMapDto> to = new ArrayList<>();
            List<?> objectList = new ArrayList<>((Collection<?>) object);
            for (Object item : objectList) {
                if (item instanceof PropertyMapDto) {
                    to.add((PropertyMapDto) item);
                }
            }
            return to;
        }
        return new ArrayList<>();
    }
}
