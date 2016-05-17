package com.comuto.validator.constraint;

import com.comuto.validator.Utils;
import org.junit.Before;
import org.junit.Test;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

public class FileConstraintTest {

    private java.io.File file;

    @Before
    public void setUp() throws Exception {
        file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void testIsValid() throws Exception {
        assertEmpty("Is valid", getFileConstraint(0, Double.MAX_VALUE).validate());
    }

    @Test
    public void testValidateWithMinConstraint() throws Exception {
        assertNotEmpty("Is invalid", getFileConstraint(100000000d, Double.MAX_VALUE).validate());
    }

    @Test
    public void testValidateWithMaxConstraint() throws Exception {
        assertNotEmpty("Is invalid", getFileConstraint(0, 0).validate());
    }

    private FileConstraint getFileConstraint(final double min, final double max) {
        FileConstraint constraint = new FileConstraint(file, "file");

        constraint.setMin(min);
        constraint.setMax(max);

        return constraint;
    }
}