package org.undertest.junit.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class UnderTestEngineTest {

    @Test
    void numberOne() {

    }

    @Test
    void numberTwo() {
        fail("I want to fail.");
    }
}
