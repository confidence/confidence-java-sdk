package org.undertest.api;

/**
 * A test harness is a carefully constructed
 */
public interface TestHarness {

    <T> T get(Class<T> type);

}
