package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

public class LengthConstraint extends Constraint<Object> {
    public static final String ERROR_CODE_TOO_SMALL = "ERROR_CODE_TOO_SMALL";
    public static final String ERROR_CODE_TOO_LARGE = "ERROR_CODE_TOO_LARGE";

    protected double min = 0;
    protected double max = Double.MAX_VALUE;

    protected String minMessage = "This value is too short. It should have %s characters or more.";
    protected String maxMessage = "This value is too long. It should have %s characters or less.";

    public LengthConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, propertyName);
    }

    /**
     * Implement the validation logic.
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
        } else if(max < value.trim().length()) {
            violations.add(new Violation(propertyName, value, String.format(minMessage, value), ERROR_CODE_TOO_LARGE));
        }

        return violations;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setMinMessage(String minMessage) {
        this.minMessage = minMessage;
    }

    public void setMaxMessage(String maxMessage) {
        this.maxMessage = maxMessage;
    }
}
