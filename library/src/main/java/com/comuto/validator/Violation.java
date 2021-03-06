package com.comuto.validator;

/**
 * Violation return by a {@code Constraint} when object value violate the constraint.
 */
public class Violation {
    private final String propertyName;
    private final Object invalidValue;
    private final String message;
    private final String code;

    /**
     * Construct the violation with final values.
     *
     * @param propertyName The name of the field. This is defined by the user.
     * @param invalidValue The invalid which have violate the constraint.
     * @param message The interpolated message.
     * @param code The Error code of the constraint.
     */
    public Violation(String propertyName, Object invalidValue, String message, String code) {
        this.propertyName = propertyName;
        this.invalidValue = invalidValue;
        this.message = message;
        this.code = code;
    }

    /**
     * @return the interpolated error message for this constraint violation
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the value failing to pass the constraint.
     * For cross-parameter constraints, an {@code Object[]} representing
     * the method invocation arguments is returned.
     *
     * @return the value failing to pass the constraint
     */
    public Object getInvalidValue() {
        return invalidValue;
    }

    /**
     * Returns the name of the property.
     *
     * @return the property name.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns a machine-digestible error code for the violation.
     *
     * @return String The error code.
     */
    public String getCode() {
        return code;
    }
}
