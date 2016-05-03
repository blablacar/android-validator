package com.comuto.validator.constraint;

import com.comuto.validator.Utils;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowBitmapFactory;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, shadows = {
    ShadowBitmapFactory.class
})
public class ImageConstraintTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        this.file = Utils.getFileFromResource(this, "test-file.png");
    }

    @Test
    public void validateIsValid() throws Exception {
        assertEmpty("Is valid", getImageConstraint(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE).validate());
    }

    @Test
    public void validateWithMinWidthConstraint() throws Exception {
        assertNotEmpty("Is invalid", getImageConstraint(400, 0, Integer.MAX_VALUE, Integer.MAX_VALUE).validate());
    }

    @Test
    public void validateWithMaxWidthConstraint() throws Exception {
        assertNotEmpty("Is invalid", getImageConstraint(0, 0, 10, Integer.MAX_VALUE).validate());
    }

    @Test
    public void validateWithMinHeightConstraint() throws Exception {
        assertNotEmpty("Is invalid", getImageConstraint(0, 300, Integer.MAX_VALUE, Integer.MAX_VALUE).validate());
    }

    @Test
    public void validateWithMaxHeightConstraint() throws Exception {
        assertNotEmpty("Is invalid", getImageConstraint(0, 0, Integer.MAX_VALUE, 10).validate());
    }

    private ImageConstraint getImageConstraint(final int minWidth, final int minHeight, final int maxWidth,
        final int maxHeight) {
        ImageConstraint constraint = new ImageConstraint(file, "file");

        constraint.setMinWidth(minWidth);
        constraint.setMinHeight(minHeight);
        constraint.setMaxWidth(maxWidth);
        constraint.setMaxHeight(maxHeight);

        return constraint;
    }
}