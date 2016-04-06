package com.comuto.validator.constraint;

import android.graphics.BitmapFactory;

import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import java.io.File;
import java.util.List;

public class ImageFileConstraint extends FileConstraint {

    public static final String ERROR_CODE_WIDTH_TOO_SMALL = "ERROR_CODE_WIDTH_TOO_SMALL";
    public static final String ERROR_CODE_WIDTH_TOO_BIG = "ERROR_CODE_WIDTH_TOO_BIG";
    public static final String ERROR_CODE_HEIGHT_TOO_SMALL = "ERROR_CODE_HEIGHT_TOO_SMALL";
    public static final String ERROR_CODE_HEIGHT_TOO_BIG = "ERROR_CODE_HEIGHT_TOO_BIG";

    private static final String DEFAULT_MIN_WIDTH_TEXT = "The image width is too small.";
    private static final String DEFAULT_MAX_WIDTH_TEXT = "The image width is too big.";
    private static final String DEFAULT_MIN_HEIGHT_TEXT = "The image height is too small.";
    private static final String DEFAULT_MAX_HEIGHT_TEXT = "The image height is too big.";

    private long minWidth = UNSET_VALUE;
    private long maxWidth = UNSET_VALUE;
    private long minHeight = UNSET_VALUE;
    private long maxHeight = UNSET_VALUE;

    private String minWidthMessage = DEFAULT_MIN_WIDTH_TEXT;
    private String maxWidthMessage = DEFAULT_MAX_WIDTH_TEXT;
    private String minHeightMessage = DEFAULT_MIN_HEIGHT_TEXT;
    private String maxHeightMessage = DEFAULT_MAX_HEIGHT_TEXT;

    public ImageFileConstraint() {
    }

    @Override
    public List<Violation> validate(Field<?, File> field) {
        final List<Violation> violations = super.validate(field);

        if (UNSET_VALUE == minWidth && UNSET_VALUE == maxWidth && UNSET_VALUE == minHeight && UNSET_VALUE == maxHeight) {
            return violations;
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeFile(field.getValue().getPath(), options);
            int width = options.outWidth;
            int height = options.outHeight;

            if (UNSET_VALUE != minWidth && width <= minWidth) {
                violations.add(new Violation(minWidthMessage, ERROR_CODE_WIDTH_TOO_SMALL, field));
            }

            if (UNSET_VALUE != maxWidth && width >= maxWidth) {
                violations.add(new Violation(maxWidthMessage, ERROR_CODE_WIDTH_TOO_BIG, field));
            }

            if (UNSET_VALUE != minHeight && height <= minHeight) {
                violations.add(new Violation(minHeightMessage, ERROR_CODE_HEIGHT_TOO_SMALL, field));
            }

            if (UNSET_VALUE != maxHeight && height >= maxHeight) {
                violations.add(new Violation(maxHeightMessage, ERROR_CODE_HEIGHT_TOO_BIG, field));
            }
        }

        return violations;
    }

    public void setMinWidth(long minWidth) {
        this.minWidth = minWidth;
    }

    public void setMaxWidth(long maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMinHeight(long minHeight) {
        this.minHeight = minHeight;
    }

    public void setMaxHeight(long maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMinWidthMessage(String minWidthMessage) {
        this.minWidthMessage = minWidthMessage;
    }

    public void setMaxWidthMessage(String maxWidthMessage) {
        this.maxWidthMessage = maxWidthMessage;
    }

    public void setMinHeightMessage(String minHeightMessage) {
        this.minHeightMessage = minHeightMessage;
    }

    public void setMaxHeightMessage(String maxHeightMessage) {
        this.maxHeightMessage = maxHeightMessage;
    }

}
