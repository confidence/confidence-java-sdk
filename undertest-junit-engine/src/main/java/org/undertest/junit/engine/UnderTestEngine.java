package org.undertest.junit.engine;

import java.util.Optional;
import org.junit.jupiter.engine.JupiterTestEngine;
import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;
import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.ExecutionRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestEngine;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnderTestEngine implements TestEngine {

    private final static Logger LOG = LoggerFactory.getLogger(UnderTestEngine.class);

    private final JupiterTestEngine embedded = new JupiterTestEngine();

    @Override
    public String getId() {
        return "org.undertest.junit.engine";
    }

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        UnderTestDescriptor desc = new UnderTestDescriptor(uniqueId,embedded.discover(discoveryRequest,uniqueId));

        return desc; //embedded.discover(discoveryRequest,uniqueId);
    }

    public UnderTestEngine() {
        super();
    }

    @Override
    public void execute(final ExecutionRequest request) {
        ExecutionRequest underTestExec = new UnderTestExecutionRequest(request, new DelegatingEngineExecutionListener(request.getEngineExecutionListener()) {
            @Override
            public void executionFinished(TestDescriptor testDescriptor, TestExecutionResult testExecutionResult) {
                super.executionFinished(testDescriptor,testExecutionResult);

                if (testExecutionResult.getStatus() != TestExecutionResult.Status.SUCCESSFUL) {
                    LOG.info("Test " + testDescriptor.getDisplayName() + " has failed: " + testExecutionResult.getThrowable().get());
                }
            }
        });
        embedded.execute(underTestExec);
    }

    @Override
    public Optional<String> getGroupId() {
        return Optional.of("org.undertest");
    }

    @Override
    public Optional<String> getArtifactId() {
        return Optional.of("undertest-junit-engine");
    }

    @Override
    public Optional<String> getVersion() {
        return Optional.empty();
    }
}
