package com.comuto.validator.constraint;

import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class MatchConstraintTest
    extends BaseConstraintTest {
    private static final String PROPERTY_NAME = "property";
    private static final Pattern PATTERN = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
    private static final String VALID = "foo";
    private static final String INVALID = "-";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new MatchConstraint(VALID, PATTERN, PROPERTY_NAME).validate());

        assertEmpty("is valid with ediText",
            new MatchConstraint(generateEditText(VALID), PATTERN, PROPERTY_NAME).validate());

        assertEmpty("is valid with spinner",
            new MatchConstraint(generateSpinner(VALID), PATTERN, PROPERTY_NAME).validate());
    }

    @Test
    public void isInValid() throws Exception {
        assertNotEmpty("is invalid with String", new MatchConstraint(INVALID, PATTERN, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with String", new MatchConstraint(NULL, PATTERN, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with EdiText",
            new MatchConstraint(generateEditText(INVALID), PATTERN, PROPERTY_NAME).validate());

        assertNotEmpty("is invalid with Spinner",
            new MatchConstraint(generateSpinner(INVALID), PATTERN, PROPERTY_NAME).validate());
    }
}