package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Validate a file to have a minimum and maximum size.
 */
public class FileConstraint extends Constraint<File> {
    protected static final double MEGABYTE = 1024L * 1024L;

    /**
     * Constant pass to Violation when file is to small.
     */
    public static final String ERROR_CODE_TOO_SMALL = "ERROR_CODE_TOO_SMALL";
    /**
     * Constant pass to Violation when file is to big.
     */
    public static final String ERROR_CODE_TOO_LARGE = "ERROR_CODE_TOO_LARGE";

    /**
     * Constant pass to Violation when file cannot be loaded.
     */
    public static final String ERROR_CODE_NOT_EXISTS = "ERROR_CODE_NOT_EXISTS";

    protected double min = 0;
    protected double max = Double.MAX_VALUE;

    protected String notExistsMessage = "This file couldn't be loaded.";
    protected String minMessage = "Please choose a file of min. %sMB.";
    protected String maxMessage = "Please choose a file of max. %sMB.";

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public FileConstraint(@NonNull File object, @NonNull String propertyName) {
        super(object, propertyName);
    }

    /**
     * Validate file size.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();

        if (!object.exists() || !object.isFile() || !object.canRead()) {
            violations.add(new Violation(propertyName, object, notExistsMessage, ERROR_CODE_NOT_EXISTS));
        } else if (object.length() <= min) {
            violations.add(new Violation(propertyName, object, String.format(minMessage, min / MEGABYTE), ERROR_CODE_TOO_SMALL));
        } else if (object.length() >= max) {
            violations.add(new Violation(propertyName, object, String.format(maxMessage, max / MEGABYTE), ERROR_CODE_TOO_LARGE));
        }

        return violations;
    }

    /**
     * Set the minimum size of the file.
     *
     * @param min minimum size in MB
     */
    public void setMin(double min) {
        this.min = min * MEGABYTE;
    }

    /**
     * Set the maximum size of the file.
     *
     * @param max maximum size in MB
     */
    public void setMax(double max) {
        this.max = max * MEGABYTE;
    }

    /**
     * Set the not exists error message when file cannot be loaded.
     *
     * @param notExistsMessage error message.
     */
    public void setNotExistsMessage(String notExistsMessage) {
        this.notExistsMessage = notExistsMessage;
    }

    /**
     * Set the minimum error message when value reach the minimum
     *
     * @param minMessage error message. This value support a string var which take min size required.
     */
    public void setMinMessage(String minMessage) {
        this.minMessage = minMessage;
    }

    /**
     * Set the maximum error message when value reach the maximum
     *
     * @param maxMessage error message. This value support a string var which take max size required.
     */
    public void setMaxMessage(String maxMessage) {
        this.maxMessage = maxMessage;
    }
}
