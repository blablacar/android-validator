package com.comuto.validator.constraint;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class IsTrueConstraintTest {

    @Test
    public void validateWithTrue() throws Exception {
        IsTrueConstraint constraint = new IsTrueConstraint("IS_TRUE");
        List<Violation> violations = constraint.validate(new Field<Void, Boolean>(null, new ArrayList<Constraint<Boolean>>()) {
            @Override
            public Boolean getValue() {
                return true;
            }
        });

        assertEquals("Constraint violations empty", 0, violations.size());
    }

    @Test
    public void validateWithFalse() throws Exception {
        IsTrueConstraint constraint = new IsTrueConstraint("IS_TRUE");
        List<Violation> violations = constraint.validate(new Field<Void, Boolean>(null, new ArrayList<Constraint<Boolean>>()) {
            @Override
            public Boolean getValue() {
                return false;
            }
        });

        assertEquals("Constraint violations not empty", 1, violations.size());
        assertEquals("Constraint first violation message:", "IS_TRUE", violations.get(0).getMessage());
    }
}