package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Spinner;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

public class EqualsConstraint extends Constraint<Object> {
    public static final String ERROR_CODE_IS_NOT_EQUALS = "ERROR_CODE_IS_NOT_EQUALS";

    protected final String expectedValue;
    protected String message = "This value should be equal to %s.";

    public EqualsConstraint(Object object, String expectedValue, String propertyName) {
        super(object, propertyName);

        this.expectedValue = expectedValue;
    }

    @Override
    @NonNull
    public Set<Violation> validate() {
        final Set<Violation> violations = new HashSet<>();
        final String value;

        if (object instanceof EditText) {
            value = ((EditText) object).getText().toString();
        } else if (object instanceof Spinner) {
            value = ((Spinner) object).getSelectedItem().toString();
        } else if (object instanceof String) {
            value = (String) object;
        } else {
            throw new UnsupportedException(this, object, propertyName);
        }

        if (!value.equals(expectedValue)) {
            violations.add(new Violation(propertyName, value, String.format(message, value), ERROR_CODE_IS_NOT_EQUALS));
        }

        return violations;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
