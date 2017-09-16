package org.undertest.core;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

public class CloseUtils {

    public static CloseUtils closer() {
        return new CloseUtils();
    }

    public void silentClose(Closeable closable) {
        try {
            closable.close();
        } catch (IOException e) {
            // TODO: record.
        }
    }

    public void silentClose(Object object) {
        findMethod(object,"close").ifPresent( (method) -> invokeMethod(object, method));
    }

    public void silentStopAndClose(Object object) {
        findMethod(object,"stop").ifPresent( (method) -> invokeMethod(object, method));
        silentClose(object);
    }

    private void invokeMethod(Object object, Method method) {
        try {
            method.invoke(object);
        } catch (Exception e) {
            // TODO: record.
        }
    }


    private Optional<Method> findMethod(Object object, String methodName) {
        for (Method m : object.getClass().getDeclaredMethods()) {
            if (methodName.equals(m.getName())) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }
}
