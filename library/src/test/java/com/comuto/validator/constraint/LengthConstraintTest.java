package com.comuto.validator.constraint;

import com.comuto.validator.Violation;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE) public class LengthConstraintTest
    extends BaseConstraintTest {

    private static final String PROPERTY_NAME = "property_length";
    private static final String VALUE = "valid";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", validateConstraint(VALUE, 1, 8));
        assertEmpty("is valid with String", validateConstraint(VALUE, 5, 5));

        assertEmpty("is valid with EditText", validateConstraint(generateEditText(VALUE), 1, 8));
        assertEmpty("is valid with EditText", validateConstraint(generateEditText(VALUE), 5, 5));

        assertEmpty("is valid with Spinner", validateConstraint(generateSpinner(VALUE), 1, 8));
        assertEmpty("is valid with Spinner", validateConstraint(generateSpinner(VALUE), 5, 5));
    }

    @Test
    public void isNotValid() throws Exception {
        assertNotEmpty("is invalid with String", validateConstraint(VALUE, 6, 8));
        assertNotEmpty("is invalid with String", validateConstraint(VALUE, 1, 3));

        assertNotEmpty("is invalid with EditText", validateConstraint(generateEditText(VALUE), 6, 8));
        assertNotEmpty("is invalid with EditText", validateConstraint(generateEditText(VALUE), 1, 3));

        assertNotEmpty("is invalid with Spinner", validateConstraint(generateSpinner(VALUE), 6, 8));
        assertNotEmpty("is invalid with Spinner", validateConstraint(generateSpinner(VALUE), 1, 3));
    }

    private Set<Violation> validateConstraint(final Object object, final double min, final double max) {
        LengthConstraint constraint = new LengthConstraint(object, PROPERTY_NAME);

        constraint.setMin(min);
        constraint.setMax(max);

        return constraint.validate();
    }
}