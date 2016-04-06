package com.comuto.validator;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FileValidatorTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void validateWithoutConstraint() throws Exception {
        FileValidator validator = new FileValidator(file);

        List<Violation> violations = validator.validate();

        assertNotNull("Validator validations not null", violations);
        assertTrue("Validator validations list is empty", violations.isEmpty());
    }

    @Test
    public void validateWithMinConstraint() throws Exception {
        FileValidator validator = new FileValidator(file);

        validator.setMinSize(1000000000000000L);
        validator.setMinSizeMessage("MIN_SIZE");

        List<Violation> violations = validator.validate();

        assertTrue("Validator validations list is not empty", violations.size() > 0);
        assertEquals("Validator validations message is same", "MIN_SIZE", violations.get(0).getMessage());
    }


    @Test
    public void validateWithMaxConstraint() throws Exception {
        FileValidator validator = new FileValidator(file);

        validator.setMaxSize(1L);
        validator.setMaxSizeMessage("MAX_SIZE");

        List<Violation> violations = validator.validate();

        assertTrue("Validator validations list is not empty", violations.size() > 0);
        assertEquals("Validator validations message is same", "MAX_SIZE", violations.get(0).getMessage());
    }
}