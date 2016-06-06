package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import java.util.regex.Pattern;

/**
 * Validate the object value to have a good Person name.
 */
public class NameConstraint extends MatchConstraint {
    protected static final Pattern PATTERN =
        Pattern.compile("^[\\p{L} .'-]+$", Pattern.CASE_INSENSITIVE);

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public NameConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, PATTERN, propertyName);
    }
}
