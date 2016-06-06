package com.comuto.validator;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ViolationTest {
    private static final Object INVALID_VALUE = new Object();
    private static final String ERROR_MESSAGE = "ERROR";
    private static final String PROPERTY = "property";
    private static final String CODE = "CODE";

    private final Violation violation;

    public ViolationTest() {
        this.violation = new Violation(PROPERTY, INVALID_VALUE, ERROR_MESSAGE, CODE);
    }

    @Test
    public void getMessage() throws Exception {
        assertEquals("Message equals", ERROR_MESSAGE, violation.getMessage());
    }

    @Test
    public void getInvalidValue() throws Exception {
        assertEquals("Invalid value equals", INVALID_VALUE, violation.getInvalidValue());
    }

    @Test
    public void getPropertyName() throws Exception {
        assertEquals("Property name equals", PROPERTY, violation.getPropertyName());
    }

    @Test
    public void getCode() throws Exception {
        assertEquals("Code equals", CODE, violation.getCode());
    }
}