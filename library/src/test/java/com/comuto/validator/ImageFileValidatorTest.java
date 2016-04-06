package com.comuto.validator;

import com.comuto.validator.constraint.ImageFileConstraint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowBitmapFactory;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, shadows = {
        ShadowBitmapFactory.class
})
public class ImageFileValidatorTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void validateWithoutConstraint() throws Exception {
        ImageFileValidator validator = new ImageFileValidator(file);

        List<Violation> violations = validator.validate();

        assertNotNull("Validator validations not null", violations);
        assertTrue("Validator validations list is empty", violations.isEmpty());
    }

    @Test
    public void validateWithMinWidthConstraint() throws Exception {
        ImageFileValidator validator = new ImageFileValidator(file);
        validator.setMinWidth(400);

        List<Violation> violations = validator.validate();

        assertNotNull("Min width reach", violations.get(0));
        assertEquals("Min width code", ImageFileConstraint.ERROR_CODE_WIDTH_TOO_SMALL, violations.get(0).getCode());
    }

    @Test
    public void validateWithMaxWidthConstraint() throws Exception {
        ImageFileValidator validator = new ImageFileValidator(file);
        validator.setMaxWidth(10);

        List<Violation> violations = validator.validate();

        assertNotNull("Max width reach", violations.get(0));
        assertEquals("Max width code", ImageFileConstraint.ERROR_CODE_WIDTH_TOO_BIG, violations.get(0).getCode());
    }

    @Test
    public void validateWithMinHeightConstraint() throws Exception {
        ImageFileValidator validator = new ImageFileValidator(file);
        validator.setMinHeight(300);

        List<Violation> violations = validator.validate();

        assertNotNull("Min width reach", violations.get(0));
        assertEquals("Min width code", ImageFileConstraint.ERROR_CODE_HEIGHT_TOO_SMALL, violations.get(0).getCode());
    }

    @Test
    public void validateWithMaxHeightConstraint() throws Exception {
        ImageFileValidator validator = new ImageFileValidator(file);
        validator.setMaxHeight(10);

        List<Violation> violations = validator.validate();

        assertNotNull("Max width reach", violations.get(0));
        assertEquals("Max width code", ImageFileConstraint.ERROR_CODE_HEIGHT_TOO_BIG, violations.get(0).getCode());
    }
}