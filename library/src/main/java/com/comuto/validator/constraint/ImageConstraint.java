package com.comuto.validator.constraint;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Validate a file to have a minimum and maximum image dimensions.
 */
public class ImageConstraint extends Constraint<File> {
    /**
     * Constant pass to Violation when image is too small.
     */
    public static final String ERROR_CODE_SIZE_TOO_SMALL = "ERROR_CODE_SIZE_TOO_SMALL";
    /**
     * Constant pass to Violation when image is too big.
     */
    public static final String ERROR_CODE_SIZE_TOO_BIG = "ERROR_CODE_SIZE_TOO_BIG";

    protected int minWidth = 0;
    protected int minHeight = 0;
    protected int maxWidth = Integer.MAX_VALUE;
    protected int maxHeight = Integer.MAX_VALUE;

    protected String minMessage = "Please choose an image of min. %d x %d pixels.";
    protected String maxMessage = "Please choose an image of max. %d x %d pixels.";

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public ImageConstraint(@NonNull File object, @NonNull String propertyName) {
        super(object, propertyName);
    }

    /**
     * Validate file image dimensions.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
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

    /**
     * Set the minimum width of the image.
     *
     * @param minWidth size in pixels.
     */
    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    /**
     * Set the minimum height of the image.
     *
     * @param minHeight size in pixels.
     */
    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    /**
     * Set the maximum width of the image.
     *
     * @param maxWidth size in pixels.
     */
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    /**
     * Set the maximum height of the image.
     *
     * @param maxHeight size in pixels.
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     * Set the minimum error message when value reach the minimum
     *
     * @param minMessage error message. This value support string vars which take min width and height required.
     */
    public void setMinMessage(String minMessage) {
        this.minMessage = minMessage;
    }

    /**
     * Set the maximum error message when value reach the maximum
     *
     * @param maxMessage error message. This value support string vars which take max width and height required.
     */
    public void setMaxMessage(String maxMessage) {
        this.maxMessage = maxMessage;
    }
}
