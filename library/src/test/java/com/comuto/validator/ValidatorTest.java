package com.comuto.validator;

import com.comuto.validator.constraint.NotBlankConstraint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ValidatorTest {

    @Test
    public void validateWithoutViolations() throws Exception {
        Validator validator = new Validator();
        validator.add(new NotBlankConstraint("Test", "string"));

        assertEquals("Has no violations", 0, validator.validate().size());
    }

    @Test
    public void validateWithViolations() throws Exception {
        Validator validator = new Validator();
        validator.add(new NotBlankConstraint("", "string"));

        assertEquals("Has violations", 1, validator.validate().size());
    }
}