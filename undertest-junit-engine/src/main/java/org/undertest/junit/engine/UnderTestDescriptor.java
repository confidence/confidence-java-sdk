package org.undertest.junit.engine;

import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.TestTag;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.hierarchical.Node;

public class UnderTestDescriptor implements TestDescriptor,Node<JupiterEngineExecutionContext> {
    private final UniqueId id;
    private final TestDescriptor embedded;

    public UnderTestDescriptor(UniqueId uniqueId, TestDescriptor embedded) {
        this.id = uniqueId;
        this.embedded = embedded;
        //
        // TODO: copy all nested descriptors into read only tree.

    }

    @Override
    public UniqueId getUniqueId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return "UnderTest Jupiter";
    }

    @Override
    public Set<TestTag> getTags() {
        return embedded.getTags();
    }

    @Override
    public Optional<TestSource> getSource() {
        return embedded.getSource();
    }

    @Override
    public Optional<TestDescriptor> getParent() {
        return embedded.getParent();
    }

    @Override
    public void setParent(TestDescriptor parent) {
        embedded.setParent(parent);
    }

    @Override
    public Set<? extends TestDescriptor> getChildren() {
        return embedded.getChildren();
    }

    @Override
    public void addChild(TestDescriptor descriptor) {
        // impossible
        throw new RuntimeException("Undertest Descriptor should get data only from embedded descriptor");
        //embedded.addChild(descriptor);
    }

    @Override
    public void removeChild(TestDescriptor descriptor) {
        throw new RuntimeException("Undertest Descriptor should get data only from embedded descriptor");

        //embedded.removeChild(descriptor);
    }

    @Override
    public void removeFromHierarchy() {
        throw new RuntimeException("Undertest Descriptor should get data only from embedded descriptor");
    }

    @Override
    public Type getType() {
        return embedded.getType();
    }

    @Override
    public Optional<? extends TestDescriptor> findByUniqueId(UniqueId uniqueId) {
        return embedded.findByUniqueId(uniqueId);
    }
}
