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

    /**
     * The `String value() default "";` line in the `ApeironAction` annotation is
     * defining a method named `value` that serves as an alias for the `value`
     * attribute of the
     * `Component` annotation. This allows you to specify the value of the
     * `Component` annotation directly
     * when using `ApeironAction` without explicitly mentioning `value`.
     * 
     */
    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     * The `public String nombre();` method declaration inside the `ApeironAction`
     * annotation is defining an abstract method named `nombre`. This method does
     * not have a body and is used to specify a required attribute for the
     * annotation. When you apply this annotation to a class, you will need to
     * provide a value for the `nombre` attribute.
     * 
     */
    public String nombre();
}
