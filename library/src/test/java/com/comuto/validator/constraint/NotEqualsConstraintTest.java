package com.comuto.validator.constraint;

import com.comuto.validator.UnsupportedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class NotEqualsConstraintTest
    extends BaseConstraintTest {
    private static final String VALID = "test";
    public static final String EXPECTED_VALUE = "not";
    private static final String PROPERTY_NAME = "property_not_equals";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new NotEqualsConstraint(VALID, EXPECTED_VALUE, PROPERTY_NAME).validate());

        assertEmpty("is valid with EditText",
            new NotEqualsConstraint(generateEditText(VALID), EXPECTED_VALUE, PROPERTY_NAME).validate());

        assertEmpty("is valid with Spinner",
            new NotEqualsConstraint(generateSpinner(VALID), EXPECTED_VALUE, PROPERTY_NAME).validate());
    }

    @Test
    public void isInvalid() throws Exception {
        assertNotEmpty("is invalid with String", new NotEqualsConstraint(VALID, VALID, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with EditText",
            new NotEqualsConstraint(generateEditText(VALID), VALID, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with Spinner",
            new NotEqualsConstraint(generateSpinner(VALID), VALID, PROPERTY_NAME).validate());
    }

    @Test(expected = UnsupportedException.class)
    public void testUnsupported() throws Exception {
        new NotEqualsConstraint(new Object(), VALID, PROPERTY_NAME).validate();
    }
}