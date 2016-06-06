package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import android.util.Patterns;

/**
 * Validate the object value to be an email.
 *
 * @see Patterns#EMAIL_ADDRESS;
 */
public class EmailConstraint extends MatchConstraint {
    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public EmailConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, Patterns.EMAIL_ADDRESS, propertyName);
    }
}
