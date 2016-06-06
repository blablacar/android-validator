package com.comuto.validator;

import com.comuto.validator.constraint.Constraint;

/**
 * Exception thrown when the library doesn't the object pass into a {@code Constraint}.
 */
public class UnsupportedException extends RuntimeException {
    /**
     * Constructs a new {@code RuntimeException} with the current stack trace
     * and the specified detail message.
     *
     * @param constraint the constraint for this exception.
     * @param o the object invalid value;
     * @param propertyName the property.
     */
    public UnsupportedException(Constraint constraint, Object o, String propertyName) {
        super("Constraint: "
            + constraint.getClass().getSimpleName()
            + " cannot handle "
            + o.getClass().getName()
            + "on property: "
            + propertyName
            + ". If you want to validate a custom Object, you can create a custom Constraint which extends this one and override validate().");
    }
}
