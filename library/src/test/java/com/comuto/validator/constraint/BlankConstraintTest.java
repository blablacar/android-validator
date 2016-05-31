package com.comuto.validator.constraint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class BlankConstraintTest
    extends BaseConstraintTest {
    private static final String PROPERTY_NAME = "property_name";
    private static final String NOT_BLANK = "not_blank";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new BlankConstraint(BLANK, PROPERTY_NAME).validate());

        assertEmpty("is valid with EditText", new BlankConstraint(generateEditText(BLANK), PROPERTY_NAME).validate());

        assertEmpty("is valid with Spinner", new BlankConstraint(generateSpinner(BLANK), PROPERTY_NAME).validate());
    }

    @Test
    public void isInvalidWithNotBlank() throws Exception {
        assertNotEmpty("is invalid with String", new BlankConstraint(NOT_BLANK, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with EditText",
            new BlankConstraint(generateEditText(NOT_BLANK), PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with Spinner",
            new BlankConstraint(generateSpinner(NOT_BLANK), PROPERTY_NAME).validate());
    }

    @Test
    public void isValidWithNull() throws Exception {
        assertEmpty("is valid with String", new BlankConstraint(NULL, PROPERTY_NAME).validate());

        assertEmpty("is valid with EditText", new BlankConstraint(generateEditText(NULL), PROPERTY_NAME).validate());

        assertEmpty("is valid with Spinner", new BlankConstraint(generateSpinner(NULL), PROPERTY_NAME).validate());
    }
}