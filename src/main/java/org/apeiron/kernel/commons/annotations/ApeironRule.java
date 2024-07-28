package org.apeiron.kernel.commons.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * Annotation for create an Apeiron rule
 *
 * @author Daniel Cortes Pichardo
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface ApeironRule {

    /**
     * The `@AliasFor` annotation in this context is used to indicate that the
     * `value` attribute in the
     * `ApeironRule` annotation is an alias for the `value` attribute in the
     * `Component` annotation. This
     * means that when you use the `ApeironRule` annotation and specify a value
     * for the `value` attribute,
     * it will be treated as if you were specifying a value for the `value`
     * attribute in the `Component`
     * annotation.
     * 
     */
    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     * The `public String nombre();` method declaration inside the `ApeironRule`
     * annotation is defining
     * a method named `nombre` that does not have an implementation. This method
     * is used to define an
     * attribute within the annotation that can be accessed when the annotation
     * is used.
     * 
     */
    public String nombre();
}
