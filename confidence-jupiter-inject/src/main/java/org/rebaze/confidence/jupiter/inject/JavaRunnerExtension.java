package org.rebaze.confidence.jupiter.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.rebaze.confidence.jrunner.ApplicationClient;

public class JavaRunnerExtension implements TestInstancePostProcessor,ParameterResolver {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        System.out.println("Wiring " + testInstance.getClass().getName());
        for (Field field : testInstance.getClass().getDeclaredFields()) {
            System.out.println("Wiring " + testInstance.getClass().getName() + "." + field.getName());


            if (field.isAnnotationPresent(RunConfiguration.class)) {
                RunConfiguration appAnnotaion = field.getAnnotation(RunConfiguration.class);

                // wire instance to the field:
                field.setAccessible(true);
                Object value = new ApplicationClient(false);
                field.set(testInstance,value);
            }
        }

    }

    // recurse!
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        for (Annotation an : parameterContext.getParameter().getAnnotations()) {
            System.out.println("Checking " + an.annotationType().getName());

            if (an.annotationType().isAnnotationPresent(RunConfiguration.class)) {
                return true;
            }

        }
        return false;

        //JavaApplication app = parameterContext.getParameter().getAnnotation(JavaApplication.class);

        // return parameterContext.getParameter().isAnnotationPresent(JavaApplication.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> type = parameterContext.getParameter().getType();
        parameterContext.getParameter().getAnnotation(RunConfiguration.class);
        return new ApplicationClient(false);
    }
}
