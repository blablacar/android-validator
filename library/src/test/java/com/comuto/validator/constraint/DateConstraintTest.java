package com.comuto.validator.constraint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class DateConstraintTest
    extends BaseConstraintTest {
    private static final String PROPERTY_NAME = "property_date";
    private static final String VALID = "2015-06-12";
    private static final String NOT_VALID = "id";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new DateConstraint(VALID, PROPERTY_NAME).validate());

        assertEmpty("is valid with EditText", new DateConstraint(generateEditText(VALID), PROPERTY_NAME).validate());

        assertEmpty("is valid with Spinner", new DateConstraint(generateSpinner(VALID), PROPERTY_NAME).validate());
    }

    @Test
    public void isNotValid() throws Exception {
        assertNotEmpty("is not valid with String", new DateConstraint(NOT_VALID, PROPERTY_NAME).validate());

        assertNotEmpty("is not valid with EditText",
            new DateConstraint(generateEditText(NOT_VALID), PROPERTY_NAME).validate());

        assertNotEmpty("is not valid with Spinner",
            new DateConstraint(generateSpinner(NOT_VALID), PROPERTY_NAME).validate());
    }
}