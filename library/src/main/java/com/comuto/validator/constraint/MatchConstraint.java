package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Validate the object value to reach the good pattern.
 */
public class MatchConstraint extends Constraint<Object> {
    /**
     * Constant pass to Violation when not match the pattern.
     */
    public static final String ERROR_CODE_NOT_MATCH = "ERROR_CODE_NOT_MATCH";

    protected final Pattern pattern;

    protected String message = "This value is not valid.";

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param pattern the Pattern to validate.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public MatchConstraint(@NonNull Object object, @NonNull Pattern pattern, @NonNull String propertyName) {
        super(object, propertyName);

        this.pattern = pattern;
    }

    /**
     * Validate if the value respect the pattern.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();
        final String value = getStringFromObject(object, propertyName);

        if (null == value || !pattern.matcher(value).matches()) {
            violations.add(new Violation(propertyName, value, message, ERROR_CODE_NOT_MATCH));
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
