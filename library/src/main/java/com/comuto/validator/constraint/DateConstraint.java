package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Validate the object value to be a date, with a defined format.
 *
 * Default format is: yyyy-MM-dd
 */
public class DateConstraint extends Constraint<Object> {
    /**
     * Constant pass to Violation when date is invalid.
     */
    public static final String ERROR_CODE_DATE_INVALID = "ERROR_CODE_DATE_INVALID";
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    private String message = "This value is not a valid date.";

    private String format;

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public DateConstraint(@NonNull Object object, @NonNull String propertyName) {
        this(object, propertyName, DEFAULT_FORMAT);
    }

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     * @param format the format of the date.
     */
    public DateConstraint(@NonNull Object object, @NonNull String propertyName, @NonNull String format) {
        super(object, propertyName);
        this.format = format;
    }

    /**
     * Validate if the value is a date.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();
        final String value = getStringFromObject(object, propertyName);

        if (null == value || !isValid(value)) {
            violations.add(new Violation(propertyName, object, message, ERROR_CODE_DATE_INVALID));
        }

        return violations;
    }

    private boolean isValid(String value) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            dateFormat.setLenient(false);
            dateFormat.parse(value);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Set error message when value violate the constraint
     *
     * @param message String message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Set date format for this constraint.
     *
     * @param format String format.
     */
    public void setFormat(String format) {
        this.format = format;
    }
}
