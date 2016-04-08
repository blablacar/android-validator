package com.comuto.validator.constraint;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileConstraint extends Constraint<File> {
    protected static final double MEGABYTE = 1024L * 1024L;

    protected static final int UNSET_VALUE = -1;

    public static final String ERROR_CODE_TOO_SMALL = "ERROR_CODE_TOO_SMALL";
    public static final String ERROR_CODE_TOO_LARGE = "ERROR_CODE_TOO_LARGE";

    private static final String DEFAULT_MIN_SIZE_TEXT = "The file is too small.";
    private static final String DEFAULT_MAX_SIZE_TEXT = "The file is too large.";

    // in MB
    private double minSize = UNSET_VALUE;
    // in MB
    private double maxSize = UNSET_VALUE;

    private String minSizeMessage = DEFAULT_MIN_SIZE_TEXT;
    private String maxSizeMessage = DEFAULT_MAX_SIZE_TEXT;

    public FileConstraint() {
    }

    /**
     * Set file min size.
     * @param minSize in MB.
     */
    public void setMinSize(double minSize) {
        this.minSize = minSize * MEGABYTE;
    }

    /**
     * Set file min size error message.
     * @param minSizeMessage String error message.
     */
    public void setMinSizeMessage(String minSizeMessage) {
        this.minSizeMessage = minSizeMessage;
    }

    /**
     * Set file max size.
     * @param maxSize in MB.
     */
    public void setMaxSize(double maxSize) {
        this.maxSize = maxSize * MEGABYTE;
    }

    /**
     * Set file max size error message.
     * @param maxSizeMessage String error message.
     */
    public void setMaxSizeMessage(String maxSizeMessage) {
        this.maxSizeMessage = maxSizeMessage;
    }

    @Override
    public List<Violation> validate(Field<?, File> field) {
        final List<Violation> violations = new ArrayList<>();
        final long size = field.getValue().length();

        if (UNSET_VALUE != minSize && size <= minSize) {
            violations.add(new Violation<>(minSizeMessage, ERROR_CODE_TOO_SMALL, field, size));
        }

        if (UNSET_VALUE != maxSize && size >= maxSize) {
            violations.add(new Violation<>(maxSizeMessage, ERROR_CODE_TOO_LARGE, field, size));
        }

        return violations;
    }
}
