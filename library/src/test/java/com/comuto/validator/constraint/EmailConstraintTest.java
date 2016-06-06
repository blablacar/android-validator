package com.comuto.validator.constraint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class EmailConstraintTest
    extends BaseConstraintTest {
    private static final String PROPERTY_NAME = "property_email";
    private static final String VALID = "peter.canvas+driver@blablacar.com";
    private static final String NOT_VALID = "to@";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new EmailConstraint(VALID, PROPERTY_NAME).validate());

        assertEmpty("is valid with EditText", new EmailConstraint(generateEditText(VALID), PROPERTY_NAME).validate());

        assertEmpty("is valid with Spinner", new EmailConstraint(generateSpinner(VALID), PROPERTY_NAME).validate());
    }

    @Test
    public void isNotValid() throws Exception {
        assertNotEmpty("is not valid with String", new EmailConstraint(NOT_VALID, PROPERTY_NAME).validate());

        assertNotEmpty("is not valid with EditText",
            new EmailConstraint(generateEditText(NOT_VALID), PROPERTY_NAME).validate());

        assertNotEmpty("is not valid with Spinner",
            new EmailConstraint(generateSpinner(NOT_VALID), PROPERTY_NAME).validate());
    }
}