package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

/**
 * Validate the object value to be not null and not empty.
 */
public class NotBlankConstraint extends Constraint<Object> {
    /**
     * Constant pass to Violation when the value is blank.
     */
    public static final String ERROR_CODE_IS_BLANK = "ERROR_CODE_IS_BLANK";

    protected String message = "This value should not be blank.";

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public NotBlankConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, propertyName);
    }

    /**
     * Validate if the value is not null and not empty.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();
        final String value = getStringFromObject(object, propertyName);

        if (null == value || 0 == value.trim().length()) {
            violations.add(new Violation(propertyName, value, message, ERROR_CODE_IS_BLANK));
        }

        return violations;
    }

    /**
     * Set error message when value violate the constraint
     *
     * @param message String message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
