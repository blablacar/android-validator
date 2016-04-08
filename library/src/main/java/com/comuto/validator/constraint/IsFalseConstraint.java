package com.comuto.validator.constraint;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import java.util.ArrayList;
import java.util.List;

public class IsFalseConstraint extends Constraint<Boolean> {
    private static final String DEFAULT_MESSAGE = "This value should be false.";

    public static final String ERROR_NOT_FALSE = "ERROR_NOT_FALSE";

    private final String message;

    public IsFalseConstraint() {
        this(DEFAULT_MESSAGE);
    }

    public IsFalseConstraint(String message) {
        this.message = message;
    }

    @Override
    protected List<Violation> validate(Field<?, Boolean> field) {
        final List<Violation> violations = new ArrayList<>();
        boolean value = field.getValue();

        if (value) {
            violations.add(new Violation<>(message, ERROR_NOT_FALSE, field, value));
        }

        return violations;
    }
}
