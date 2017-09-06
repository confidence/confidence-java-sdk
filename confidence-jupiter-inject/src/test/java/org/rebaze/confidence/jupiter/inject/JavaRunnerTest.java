package org.rebaze.confidence.jupiter.inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rebaze.confidence.jrunner.ApplicationClient;

@ExtendWith(JavaRunnerExtension.class)
public class JavaRunnerTest {

    @RunConfiguration(bndrun="../myapp.bndrun")
    private ApplicationClient appWorkspace;

    @RunConfiguration(jar="http://localhost:8080/foo.jar")
    private ApplicationClient app;

    @RunConfiguration(groupId = "com.foo", artifactId="bee", version="1.0")
    private ApplicationClient appGav;

    @Test
    public void testLauncher(@TigerConfiguration ApplicationClient run) {

    }

    @Test
    public void testLauncher2() {

    }
}
