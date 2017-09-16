package org.undertest.junit.engine;

import java.util.Optional;
import java.util.Set;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.TestTag;
import org.junit.platform.engine.UniqueId;

public class UnderTestDescriptor implements TestDescriptor {
    private final UniqueId id;

    public UnderTestDescriptor(UniqueId uniqueId) {
        this.id = uniqueId;
    }

    @Override
    public UniqueId getUniqueId() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public Set<TestTag> getTags() {
        return null;
    }

    @Override
    public Optional<TestSource> getSource() {
        return null;
    }

    @Override
    public Optional<TestDescriptor> getParent() {
        return null;
    }

    @Override
    public void setParent(TestDescriptor parent) {

    }

    @Override
    public Set<? extends TestDescriptor> getChildren() {
        return null;
    }

    @Override
    public void addChild(TestDescriptor descriptor) {

    }

    @Override
    public void removeChild(TestDescriptor descriptor) {

    }

    @Override
    public void removeFromHierarchy() {

    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Optional<? extends TestDescriptor> findByUniqueId(UniqueId uniqueId) {
        return null;
    }
}
