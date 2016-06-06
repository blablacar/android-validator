package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

/**
 * Validate the object value to have a correct length.
 */
public class LengthConstraint extends Constraint<Object> {
    /**
     * Constant pass to Violation when length is too small.
     */
    public static final String ERROR_CODE_TOO_SMALL = "ERROR_CODE_TOO_SMALL";
    /**
     * Constant pass to Violation when the length value is too large.
     */
    public static final String ERROR_CODE_TOO_LARGE = "ERROR_CODE_TOO_LARGE";

    protected double min = 0;
    protected double max = Double.MAX_VALUE;

    protected String minMessage = "This value is too short. It should have %s characters or more.";
    protected String maxMessage = "This value is too long. It should have %s characters or less.";

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public LengthConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, propertyName);
    }

    /**
     * Validate if the value has reach the excepted values.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();
        final String value = getStringFromObject(object, propertyName);

        if (null == value || min > value.trim().length()) {
            violations.add(new Violation(propertyName, value, String.format(minMessage, value), ERROR_CODE_TOO_SMALL));
        } else if (max < value.trim().length()) {
            violations.add(new Violation(propertyName, value, String.format(minMessage, value), ERROR_CODE_TOO_LARGE));
        }

        return violations;
    }

    /**
     * Set minimum length of the object.
     *
     * @param min minimum length
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * Set maximum length of the object.
     *
     * @param max minimum length
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * Set the minimum error message when value reach the minimum
     *
     * @param minMessage error message. This value support a string var which take min length required.
     */
    public void setMinMessage(String minMessage) {
        this.minMessage = minMessage;
    }

    /**
     * Set the maximum error message when value reach the maximum
     *
     * @param maxMessage error message. This value support a string var which take max length required.
     */
    public void setMaxMessage(String maxMessage) {
        this.maxMessage = maxMessage;
    }
}
