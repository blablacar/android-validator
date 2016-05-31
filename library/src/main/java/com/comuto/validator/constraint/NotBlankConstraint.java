package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

public class NotBlankConstraint extends Constraint<Object> {
    public static final String ERROR_CODE_IS_BLANK = "ERROR_CODE_IS_BLANK";

    protected String message = "This value should not be blank.";

    public NotBlankConstraint(Object object, String propertyName) {
        super(object, propertyName);
    }

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

    public void setMessage(String message) {
        this.message = message;
    }
}
