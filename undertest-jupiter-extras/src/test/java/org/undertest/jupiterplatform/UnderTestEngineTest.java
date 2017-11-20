package org.undertest.jupiterplatform;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UnderTestEngineTest {

    @Test
    void numberOne() {
        ProcessHandle self = ProcessHandle.current();

        long PID = self.pid();
        ProcessHandle.Info procInfo = self.info();


        Optional<String[]> args = procInfo.arguments();
        Optional<String> cmd =  procInfo.commandLine();
        Optional<Instant> startTime = procInfo.startInstant();
        Optional<Duration> cpuUsage = procInfo.totalCpuDuration();

        assertEquals("",cmd.get());
        assertEquals(1,PID);

        //Actual   :/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/bin/java
        // -ea -Didea.test.cyclic.buffer.size=1048576
        // -javaagent:/Applications/IntelliJ IDEA 2017.3 EAP.app/Contents/lib/idea_rt.jar=58794:/Applications/IntelliJ IDEA 2017.3 EAP.app/Contents/bin
        // -Dfile.encoding=UTF-8
        // -classpath

        // /Applications/IntelliJ IDEA 2017.3 EAP.app/Contents/lib/idea_rt.jar:
        // /Applications/IntelliJ IDEA 2017.3 EAP.app/Contents/plugins/junit/lib/junit-rt.jar:
        // /Applications/IntelliJ IDEA 2017.3 EAP.app/Contents/plugins/junit/lib/junit5-rt.jar:
        // /Users/tonit/.m2/repository/org/junit/platform/junit-platform-launcher/1.0.1/junit-platform-launcher-1.0.1.jar:
        // /Users/tonit/.m2/repository/org/junit/platform/junit-platform-engine/1.0.1/junit-platform-engine-1.0.1.jar:
        // /Users/tonit/.m2/repository/org/junit/platform/junit-platform-commons/1.0.1/junit-platform-commons-1.0.1.jar:
        // /Users/tonit/.m2/repository/org/opentest4j/opentest4j/1.0.0/opentest4j-1.0.0.jar:
        // /Users/tonit/devel/rebaze/auxis/undertest-jupiter-extras/out/test/classes:
        // /Users/tonit/devel/rebaze/auxis/undertest-jupiter-extras/out/production/classes:
        // /Users/tonit/.m2/repository/org/ops4j/base/ops4j-base-io/1.5.0/ops4j-base-io-1.5.0.jar:
        // /Users/tonit/.m2/repository/org/projectlombok/lombok/1.16.18/lombok-1.16.18.jar:
        // /Users/tonit/.m2/repository/org/slf4j/slf4j-api/1.5.11/slf4j-api-1.5.11.jar:
        // /Users/tonit/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-api/5.0.1/30fbf4448c594cb45d8e91630d6bb7f465906b88/junit-jupiter-api-5.0.1.jar:
        // /Users/tonit/.m2/repository/org/ops4j/base/ops4j-base-lang/1.5.0/ops4j-base-lang-1.5.0.jar:
        // /Users/tonit/.m2/repository/org/ops4j/base/ops4j-base-monitors/1.5.0/ops4j-base-monitors-1.5.0.jar:
        // /Users/tonit/devel/rebaze/auxis/undertest-junit-engine/out/test/classes:
        // /Users/tonit/devel/rebaze/auxis/undertest-junit-engine/out/production/classes:
        // /Users/tonit/devel/rebaze/auxis/undertest-junit-engine/out/production/resources:
        // /Users/tonit/devel/rebaze/auxis/undertest-junit-api/out/test/classes:
        // /Users/tonit/devel/rebaze/auxis/undertest-junit-api/out/production/classes:
        // /Users/tonit/devel/rebaze/auxis/undertest-junit-api/out/production/resources:
        // /Users/tonit/.m2/repository/ch/qos/logback/logback-classic/0.9.20/logback-classic-0.9.20.jar:
        // /Users/tonit/.m2/repository/ch/qos/logback/logback-core/0.9.20/logback-core-0.9.20.jar:
        // /Users/tonit/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-engine/5.0.1/e22d2bf87f43fb03fee14dea447b2a14f41b095f/junit-jupiter-engine-5.0.1.jar

        // com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 -junit5 org.undertest.jupiterplatform.UnderTestEngineTest,numberOne
        //
    }

    @Test
    void numberTwo() {
        fail("I want to fail.");
    }
}
