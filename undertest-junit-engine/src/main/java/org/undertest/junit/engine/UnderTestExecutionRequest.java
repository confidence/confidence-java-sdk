package org.undertest.junit.engine;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.ExecutionRequest;

public class UnderTestExecutionRequest extends ExecutionRequest {
    public UnderTestExecutionRequest(ExecutionRequest request,EngineExecutionListener listener) {
        super(request.getRootTestDescriptor(),listener,request.getConfigurationParameters());

    }

}
