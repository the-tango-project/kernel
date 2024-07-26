package org.apeiron.kernel.commons.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * Annotation for create an Apeiron Actions
 *
 * @author Daniel Cortes Pichardo
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface ApeironAction {
    @AliasFor(annotation = Component.class)
    String value() default "";

    public String nombre();
}
