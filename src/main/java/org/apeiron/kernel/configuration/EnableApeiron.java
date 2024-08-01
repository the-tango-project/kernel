package org.apeiron.kernel.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * This annotation allow to the users take control over the way of how aperion
 * load the application by just adding this annotation into a configuration
 * class and configurate the aperion modules.
 * 
 * We prefer use this annotation rather than Autoconfiguration.
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ AppRegistrar.class, JobrunrConfiguration.class, MailConfiguration.class })
public @interface EnableApeiron {

}
