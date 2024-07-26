package org.apeiron.kernel.commons.annotations;

import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.validator.IRule;
import org.apeiron.kernel.utils.StringUtils;
import org.apeiron.kernel.web.rest.ActionResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * Clase de contexto que se utiliza cuando se procesas las anotaciones
 * {@link Arguments} y {@link Argument} a través de las clases
 * {@link AnnotationProcessor#resolveActions(IAction)},
 * {@link ActionResource#getAllActions(Pageable, ServerHttpRequest)}
 *
 *
 * Las anotaciones se buscan unicamente en clases que implementen la interfaz
 * {@link IAction}.
 */
@Data
@Builder
public class ActionAnnotationContext implements Configurable {

    /**
     * Clave que se le asigna a una anotación como referencia. Este valor se
     * construye a partir del nombre de la clase que implementa una anotación
     * {@link IRule} o {@link IAction} en formato CamelCase. Para estandarizar el
     * nombre de la clave, se utiliza la función
     * {@link StringUtils#toCamelCase(String)} al nombre simple de la clase
     * {@link Class#getSimpleName()}
     *
     * <pre>{@code
     * String clave = StringUtils.toCamelCase(clazz.getSimpleName());
     * }</pre>
     */
    private String clave;

    /**
     * Variable que almacena el Optional de la anotación {@link ApeironAction} en
     * caso de que exista su definición en una clase que implemente la intefaz
     * {@link IAction}
     */
    private Optional<ApeironAction> action;

    /**
     * Variable que almacena el Optional de la anotación {@link Deprecated} en
     * caso de que exista su definición. Una {@link IRule} o {@link IAction} anotada
     * como deprecated significa que será removida en próximas versiones, por lo
     * tanto, se deberá de indicar la nueva {@link IRule} o {@link IAction} que se
     * será utilizada en su lugar.
     */
    private Optional<Deprecated> deprecated;

    /**
     * Variable que almacena el Optional de la anotación {@link Arguments} en
     * caso de que exista su definición en un {@link IRule} o {@link IAction}
     */
    private Optional<Arguments> arguments;

    /**
     * Variable que almacena el Optional de la anotación {@link Argument} en
     * caso de que exista su definición en un {@link IRule} o {@link IAction}
     */
    private Optional<Argument> argument;
}
