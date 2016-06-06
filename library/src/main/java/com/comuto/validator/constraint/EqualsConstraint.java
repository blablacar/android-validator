package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

public class EqualsConstraint extends Constraint<Object> {
    public static final String ERROR_CODE_IS_NOT_EQUALS = "ERROR_CODE_IS_NOT_EQUALS";

    protected final String expectedValue;
    protected String message = "This value should be equal to %s.";

    public EqualsConstraint(@NonNull Object object, @NonNull String expectedValue, @NonNull String propertyName) {
        super(object, propertyName);

        this.expectedValue = expectedValue;
    }

    @Override
    @NonNull
    public Set<Violation> validate() {
        final Set<Violation> violations = new HashSet<>();
        final String value = getStringFromObject(object, propertyName);

        if (!expectedValue.equalsIgnoreCase(value)) {
            violations.add(new Violation(propertyName, value, String.format(message, value), ERROR_CODE_IS_NOT_EQUALS));
        }

        return violations;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
