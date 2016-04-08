package com.comuto.validator.constraint;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import java.util.ArrayList;
import java.util.List;

public class NotBlankConstraint extends Constraint<String> {

    public static final String ERROR_CODE_IS_BLANK = "ERROR_CODE_IS_BLANK";

    private static final String DEFAULT_MESSAGE = "This value should not be blank.";

    private final String message;

    public NotBlankConstraint() {
        this(DEFAULT_MESSAGE);
    }

    public NotBlankConstraint(String message) {
        this.message = message;

    }

    @Override
    public List<Violation> validate(Field<?, String> field) {
        final List<Violation> violations = new ArrayList<>();

        if (null == field.getValue() || field.getValue().trim().isEmpty()) {
            violations.add(new Violation<>(message, ERROR_CODE_IS_BLANK, field, field.getValue()));
        }

        return violations;
    }
}
