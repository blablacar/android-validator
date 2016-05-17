package com.comuto.validator.constraint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class NameConstraintTest {
    private static final String VALID = "Peter Canvas";
    private static final String INVALID = "()Test";

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid", new NameConstraint(VALID, "string").validate());
    }

    @Test
    public void isInValid() throws Exception {
        assertNotEmpty("is invalid", new NameConstraint(INVALID, "string").validate());
    }

}