package com.comuto.validator;

public class Violation<T, V> {
    private final String message;

    private final String code;

    private final Field<?, T> field;

    private final V invalidValue;

    public Violation(String message, String code, Field<?, T> field, V invalidValue) {
        this.message = message;
        this.code = code;
        this.field = field;
        this.invalidValue = invalidValue;
    }

    /**
     * Returns the violation message.
     *
     * @return String The violation message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns a machine-digestible error code for the violation.
     *
     * @return String The error code.
     */
    public String getCode() {
        return code;
    }


    /**
     * Returns the field element of the validation
     *
     * @return Field The field element of the form which constraint has a violation.
     */
    public Field<?, T> getField() {
        return field;
    }

    public V getInvalidValue() {
        return invalidValue;
    }

}
