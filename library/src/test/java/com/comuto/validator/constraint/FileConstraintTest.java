package com.comuto.validator.constraint;

import com.comuto.validator.Utils;
import com.comuto.validator.Violation;
import com.comuto.validator.field.FileField;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FileConstraintTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void testValidateWithNoConstraint() throws Exception {
        List<Violation> violations = new FileConstraint().validate(new FileField(file));

        assertNotNull("Constraint violations not null", violations);
        assertTrue("Constraint violations empty", violations.isEmpty());
    }

    @Test
    public void testValidateWithMinConstraint() throws Exception {
        FileConstraint constraint = new FileConstraint();
        constraint.setMinSize(100000000000L);
        constraint.setMinSizeMessage("MIN_SIZE");
        List<Violation> violations = constraint.validate(new FileField(file));

        assertEquals("Constraint violations empty", 1, violations.size());
        assertEquals("Constraint first violation message:",  "MIN_SIZE", violations.get(0).getMessage());
    }

    @Test
    public void testValidateWithMaxConstraint() throws Exception {
        FileConstraint constraint = new FileConstraint();
        constraint.setMaxSize(0);
        constraint.setMaxSizeMessage("MAX_SIZE");
        List<Violation> violations = constraint.validate(new FileField(file));

        assertEquals("Constraint violations empty", 1, violations.size());
        assertEquals("Constraint first violation message:", "MAX_SIZE", violations.get(0).getMessage());
    }
}