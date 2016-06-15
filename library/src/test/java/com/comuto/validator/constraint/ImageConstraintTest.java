package com.comuto.validator.constraint;

import android.annotation.TargetApi;
import com.comuto.validator.Utils;
import com.comuto.validator.Violation;
import java.io.File;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowBitmapFactory;

import static android.os.Build.VERSION_CODES.GINGERBREAD;
import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;
import static com.comuto.validator.constraint.ImageConstraint.ERROR_CODE_NOT_EXISTS;
import static com.comuto.validator.constraint.ImageConstraint.ERROR_CODE_SIZE_TOO_BIG;
import static com.comuto.validator.constraint.ImageConstraint.ERROR_CODE_SIZE_TOO_SMALL;
import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE, shadows = {
    ShadowBitmapFactory.class
}) public class ImageConstraintTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        this.file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void validateIsValid() throws Exception {
        assertEmpty("Is valid", getImageConstraint(file, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE).validate());
    }

    @Test
    public void validateWithMinWidthConstraint() throws Exception {
        Set<Violation> violations = getImageConstraint(file, 400, 30, Integer.MAX_VALUE, Integer.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is expected one", ERROR_CODE_SIZE_TOO_SMALL, firstViolation.getCode());
        assertEquals("Message is expected one", "Please choose an image of min. 400 x 30 pixels.", firstViolation.getMessage());
    }

    @Test
    public void validateWithMaxWidthConstraint() throws Exception {
        Set<Violation> violations = getImageConstraint(file, 0, 0, 10, Integer.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is expected one", ERROR_CODE_SIZE_TOO_BIG, firstViolation.getCode());
        assertEquals("Message is expected one", "Please choose an image of max. 10 x 2147483647 pixels.", firstViolation.getMessage());
    }

    @Test
    public void validateWithMinHeightConstraint() throws Exception {
        Set<Violation> violations = getImageConstraint(file, 10, 300, Integer.MAX_VALUE, Integer.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is expected one", ERROR_CODE_SIZE_TOO_SMALL, firstViolation.getCode());
        assertEquals("Message is expected one", "Please choose an image of min. 10 x 300 pixels.", firstViolation.getMessage());
    }

    @Test
    public void validateWithMaxHeightConstraint() throws Exception {
        Set<Violation> violations = getImageConstraint(file, 0, 0, Integer.MAX_VALUE, 10).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is expected one", ERROR_CODE_SIZE_TOO_BIG, firstViolation.getCode());
        assertEquals("Message is expected one", "Please choose an image of max. 2147483647 x 10 pixels.", firstViolation.getMessage());
    }

    @Test
    public void testValidateWithNonExistingFile() throws Exception {
        Set<Violation> violations =
            getImageConstraint(new File(""), 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is excepted one", ERROR_CODE_NOT_EXISTS, firstViolation.getCode());
        assertEquals("Message is expected one", "This file couldn't be loaded.", firstViolation.getMessage());
    }

    @TargetApi(GINGERBREAD)
    @Test
    public void testValidateWithNonReadableFile() throws Exception {
        file.setReadable(false);
        Set<Violation> violations = getImageConstraint(file, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE).validate();
        assertNotEmpty("Is invalid", violations);

        Violation firstViolation = violations.iterator().next();
        assertEquals("Code is excepted one", ERROR_CODE_NOT_EXISTS, firstViolation.getCode());
        assertEquals("Message is expected one", "This file couldn't be loaded.", firstViolation.getMessage());
    }

    private static ImageConstraint getImageConstraint(File file, int minWidth, int minHeight, int maxWidth,
        int maxHeight) {
        ImageConstraint constraint = new ImageConstraint(file, "file");

        constraint.setMinWidth(minWidth);
        constraint.setMinHeight(minHeight);
        constraint.setMaxWidth(maxWidth);
        constraint.setMaxHeight(maxHeight);

        return constraint;
    }
}