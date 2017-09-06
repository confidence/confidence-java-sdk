package org.rebaze.confidence.jupiter.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER })
public @interface RunConfiguration {
    String bndrun() default "";

    String jar() default "";

    String groupId() default "";

    String artifactId() default "";

    String version() default "";

    String mainClass() default "";

}
