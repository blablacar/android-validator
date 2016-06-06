package com.comuto.validator.constraint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class UrlConstraintTest
    extends BaseConstraintTest {
    private static final String PROPERTY_NAME = "property_url";
    private static final String VALID = "http://www.blablacar.com";
    private static final String NOT_VALID = "http://";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new UrlConstraint(VALID, PROPERTY_NAME).validate());

        assertEmpty("is valid with EditText", new UrlConstraint(generateEditText(VALID), PROPERTY_NAME).validate());

        assertEmpty("is valid with Spinner", new UrlConstraint(generateSpinner(VALID), PROPERTY_NAME).validate());
    }

    @Test
    public void isNotValid() throws Exception {
        assertNotEmpty("is not valid with String", new UrlConstraint(NOT_VALID, PROPERTY_NAME).validate());

        assertNotEmpty("is not valid with EditText",
            new UrlConstraint(generateEditText(NOT_VALID), PROPERTY_NAME).validate());

        assertNotEmpty("is not valid with Spinner",
            new UrlConstraint(generateSpinner(NOT_VALID), PROPERTY_NAME).validate());
    }
}