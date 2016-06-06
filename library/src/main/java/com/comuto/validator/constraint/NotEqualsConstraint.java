package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

/**
 * Validate the object value to be not equals to the excepted one.
 */
public class NotEqualsConstraint extends Constraint<Object> {
    /**
     * Constant pass to Violation when value is not equals to the excepted one.
     */
    public static final String ERROR_CODE_EQUALS = "ERROR_CODE_EQUALS";

    protected final String expectedValue;
    protected String message = "This value should not be equal to %s.";

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param expectedValue the String value you except.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public NotEqualsConstraint(@NonNull Object object, @NonNull String expectedValue, @NonNull String propertyName) {
        super(object, propertyName);

        this.expectedValue = expectedValue;
    }

    /**
     * Validate if the value is not equals to the expected one.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @Override
    @NonNull
    public Set<Violation> validate() {
        final Set<Violation> violations = new HashSet<>();
        final String value = getStringFromObject(object, propertyName);

        if (expectedValue.equals(value)) {
            violations.add(new Violation(propertyName, value, String.format(message, value), ERROR_CODE_EQUALS));
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
