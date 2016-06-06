package com.comuto.validator.constraint;

import com.comuto.validator.UnsupportedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class NotBlankConstraintTest
    extends BaseConstraintTest {

    private static final String VALID = "test";
    private static final String PROPERTY_NAME = "property_not_blank";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new NotBlankConstraint(VALID, PROPERTY_NAME).validate());

        assertEmpty("is valid with EditText",
            new NotBlankConstraint(generateEditText(VALID), PROPERTY_NAME).validate());

        assertEmpty("is valid with Spinner", new NotBlankConstraint(generateSpinner(VALID), PROPERTY_NAME).validate());
    }

    @Test
    public void isInvalidWithBlank() throws Exception {
        assertNotEmpty("is invalid with String", new NotBlankConstraint(BLANK, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with EditText",
            new NotBlankConstraint(generateEditText(BLANK), PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with Spinner",
            new NotBlankConstraint(generateSpinner(BLANK), PROPERTY_NAME).validate());
    }

    @Test
    public void isInvalidWithNull() throws Exception {
        assertNotEmpty("is invalid with String", new NotBlankConstraint(NULL, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with EditText",
            new NotBlankConstraint(generateEditText(NULL), PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with Spinner",
            new NotBlankConstraint(generateSpinner(NULL), PROPERTY_NAME).validate());
    }

    @Test(expected = UnsupportedException.class)
    public void testUnsupported() throws Exception {
        new NotBlankConstraint(new Object(), PROPERTY_NAME).validate();
    }
}