package org.undertest.junit.engine;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.reporting.ReportEntry;

public class DelegatingEngineExecutionListener implements EngineExecutionListener {
    private final EngineExecutionListener delegate;

    public DelegatingEngineExecutionListener(EngineExecutionListener delegate) {
        this.delegate = delegate;
    }

    @Override
    public void dynamicTestRegistered(TestDescriptor testDescriptor) {
        delegate.dynamicTestRegistered(testDescriptor);
    }

    @Override
    public void executionSkipped(TestDescriptor testDescriptor, String reason) {
        delegate.executionSkipped(testDescriptor, reason);
    }

    @Override
    public void executionStarted(TestDescriptor testDescriptor) {
        delegate.executionStarted(testDescriptor);

    }

    @Override
    public void executionFinished(TestDescriptor testDescriptor, TestExecutionResult testExecutionResult) {
        delegate.executionFinished(testDescriptor, testExecutionResult);

    }

    @Override
    public void reportingEntryPublished(TestDescriptor testDescriptor, ReportEntry entry) {
        delegate.reportingEntryPublished(testDescriptor, entry);
    }
}
