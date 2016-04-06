package com.comuto.validator;

public class Violation {
    private final String message;

    private final String code;

    private final Field field;

    public Violation(String message, String code, Field field) {
        this.message = message;
        this.code = code;
        this.field = field;
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
    public Field getField() {
        return field;
    }
}
