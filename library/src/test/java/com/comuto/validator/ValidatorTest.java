package com.comuto.validator;

import com.comuto.validator.field.FileField;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class ValidatorTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void testValidate() throws Exception {
        Validator validator = new Validator();
        validator.add(new FileField(file));

        assertNotNull("Validator validations not null", validator.validate());
        assertTrue("Validator validations list is empty", validator.validate().isEmpty());
    }

}