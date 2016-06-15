package com.comuto.validator.constraint;

import android.annotation.TargetApi;
import com.comuto.validator.Utils;
import com.comuto.validator.Violation;
import java.io.File;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static android.os.Build.VERSION_CODES.GINGERBREAD;
import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;
import static com.comuto.validator.constraint.FileConstraint.ERROR_CODE_NOT_EXISTS;
import static com.comuto.validator.constraint.FileConstraint.ERROR_CODE_TOO_LARGE;
import static com.comuto.validator.constraint.FileConstraint.ERROR_CODE_TOO_SMALL;
import static junit.framework.Assert.assertEquals;

public class FileConstraintTest {

    private java.io.File file;

    @Before
    public void setUp() throws Exception {
        file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void testIsValid() throws Exception {
        assertEmpty("Is valid", getFileConstraint(file, 0, Double.MAX_VALUE).validate());
    }

    @Test
    public void testValidateWithMinConstraint() throws Exception {
        Set<Violation> violations = getFileConstraint(file, 5, Double.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is expected one", ERROR_CODE_TOO_SMALL, firstViolation.getCode());
        assertEquals("Message is expected one", "Please choose a file of min. 5.0MB.", firstViolation.getMessage());
    }

    @Test
    public void testValidateWithMaxConstraint() throws Exception {
        Set<Violation> violations = getFileConstraint(file, 0, 0).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is excepted one", ERROR_CODE_TOO_LARGE, firstViolation.getCode());
        assertEquals("Message is expected one", "Please choose a file of max. 0.0MB.", firstViolation.getMessage());
    }

    @Test
    public void testValidateWithNonExistingFile() throws Exception {
        Set<Violation> violations = getFileConstraint(new File(""), 0, Double.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is excepted one", ERROR_CODE_NOT_EXISTS, firstViolation.getCode());
        assertEquals("Message is expected one", "This file couldn't be loaded.", firstViolation.getMessage());
    }

    @TargetApi(GINGERBREAD)
    @Test
    public void testValidateWithNonReadableFile() throws Exception {
        file.setReadable(false);
        Set<Violation> violations = getFileConstraint(file, 0, Double.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is excepted one", ERROR_CODE_NOT_EXISTS, firstViolation.getCode());
        assertEquals("Message is expected one", "This file couldn't be loaded.", firstViolation.getMessage());
    }

    private static FileConstraint getFileConstraint(File file, double min, double max) {
        FileConstraint constraint = new FileConstraint(file, "file");

        constraint.setMin(min);
        constraint.setMax(max);

        return constraint;
    }
}