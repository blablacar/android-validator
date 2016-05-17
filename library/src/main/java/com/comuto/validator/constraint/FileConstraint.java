package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileConstraint extends Constraint<File> {
    protected static final double MEGABYTE = 1024L * 1024L;

    public static final String ERROR_CODE_TOO_SMALL = "ERROR_CODE_TOO_SMALL";
    public static final String ERROR_CODE_TOO_LARGE = "ERROR_CODE_TOO_LARGE";

    protected double min = 0;
    protected double max = Double.MAX_VALUE;

    protected String minMessage = "Please choose a file of min. %sMB.";
    protected String maxMessage = "Please choose a file of max. %sMB.";

    public FileConstraint(File object, String propertyName) {
        super(object, propertyName);
    }

    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();

        final long size = object.length();

        if (size <= min) {
            violations.add(new Violation(propertyName, object, String.format(minMessage, min), ERROR_CODE_TOO_SMALL));
        } else if (size >= max) {
            violations.add(new Violation(propertyName, object, String.format(maxMessage, max), ERROR_CODE_TOO_LARGE));
        }

        return violations;
    }

    /**
     * @param min minimum size in MB
     */
    public void setMin(double min) {
        this.min = min * MEGABYTE;
    }

    /**
     * @param max maximum size in MB
     */
    public void setMax(double max) {
        this.max = max * MEGABYTE;
    }

    public void setMinMessage(String minMessage) {
        this.minMessage = minMessage;
    }

    public void setMaxMessage(String maxMessage) {
        this.maxMessage = maxMessage;
    }
}
