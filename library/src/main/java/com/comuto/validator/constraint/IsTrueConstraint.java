package com.comuto.validator.constraint;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import java.util.ArrayList;
import java.util.List;

public class IsTrueConstraint extends Constraint<Boolean> {
    private static final String DEFAULT_MESSAGE = "This value should be true.";

    public static final String ERROR_NOT_TRUE = "ERROR_NOT_TRUE";

    private final String message;

    public IsTrueConstraint() {
        this(DEFAULT_MESSAGE);
    }

    public IsTrueConstraint(String message) {
        this.message = message;
    }

    @Override
    protected List<Violation> validate(Field<?, Boolean> field) {
        final List<Violation> violations = new ArrayList<>();
        boolean value = field.getValue();

        if (!value) {
            violations.add(new Violation(message, ERROR_NOT_TRUE, field));
        }

        return violations;
    }


}
