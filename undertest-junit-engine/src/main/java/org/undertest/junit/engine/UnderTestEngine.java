package org.undertest.junit.engine;

import java.util.Optional;
import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.ExecutionRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestEngine;
import org.junit.platform.engine.UniqueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnderTestEngine implements TestEngine {

    private final static Logger LOG = LoggerFactory.getLogger(UnderTestEngine.class);

    @Override
    public String getId() {
        return "org.undertest.junit.engine";
    }

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        LOG.info("Discovery started");
        TestDescriptor engineDescriptor = new UnderTestDescriptor(uniqueId);
        resolveDiscoveryRequest(discoveryRequest, engineDescriptor);
        return engineDescriptor;
    }

    private void resolveDiscoveryRequest(EngineDiscoveryRequest discoveryRequest, TestDescriptor engineDescriptor) {

    }

    @Override
    public void execute(ExecutionRequest request) {

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
