package com.comuto.validator.constraint;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ImageConstraint extends Constraint<File> {
    public static final String ERROR_CODE_SIZE_TOO_SMALL = "ERROR_CODE_SIZE_TOO_SMALL";
    public static final String ERROR_CODE_SIZE_TOO_BIG = "ERROR_CODE_SIZE_TOO_BIG";

    protected int minWidth = 0;
    protected int minHeight = 0;
    protected int maxWidth = Integer.MAX_VALUE;
    protected int maxHeight = Integer.MAX_VALUE;

    protected String minMessage = "Please choose an image of min. %d x %d pixels.";
    protected String maxMessage = "Please choose an image of max. %d x %d pixels.";

    public ImageConstraint(@NonNull File object, @NonNull String propertyName) {
        super(object, propertyName);
    }

    @Override
    @NonNull
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();

        if (0 == minWidth && 0 == minHeight && Integer.MAX_VALUE == maxWidth && Integer.MAX_VALUE == maxHeight) {
            return violations;
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeFile(object.getPath(), options);
            final int width = options.outWidth;
            final int height = options.outHeight;

            if ((0 != minWidth && width <= minWidth) || (0 != minHeight && height <= minHeight)) {
                violations.add(new Violation(propertyName, object, String.format(minMessage, minWidth, minHeight),
                    ERROR_CODE_SIZE_TOO_SMALL));
            } else if ((0 != maxWidth && width >= maxWidth) || (0 != maxHeight && height >= maxHeight)) {
                violations.add(new Violation(propertyName, object, String.format(maxMessage, maxWidth, maxHeight),
                    ERROR_CODE_SIZE_TOO_BIG));
            }
        }

        return violations;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMinMessage(String minMessage) {
        this.minMessage = minMessage;
    }

    public void setMaxMessage(String maxMessage) {
        this.maxMessage = maxMessage;
    }
}
