package com.comuto.validator;

import com.comuto.validator.constraint.NotBlankConstraint;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class ConstraintTest {

    @Test
    public void testName() throws Exception {
        NotBlankConstraint constraint = new NotBlankConstraint();
        constraint.validate(new Field<String, String>("test", new ArrayList<Constraint<String>>()) {
            @Override
            public String getValue() {
                return "test";
            }
        });

        assertEquals("Constraint name is the same", NotBlankConstraint.class.getSimpleName(), constraint.getName());
    }
}