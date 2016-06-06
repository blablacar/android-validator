package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

/**
 * Validate a view to be checked.
 * @param <T> Object should extends from View.
 */
public class IsCheckedConstraint<T extends View> extends Constraint<T> {
    /**
     * Constant pass to Violation when object is not check.
     */
    public static final String ERROR_CODE_IS_NOT_CHECKED = "ERROR_CODE_IS_NOT_CHECKED";

    protected String message = "This should have at least one checked choice.";

    /**
     * Constructor of the constraint
     *
     * @param object the object to validate through this constraint.
     * @param propertyName the property name of your field, this allow you to have the name of the field in violation.
     */
    public IsCheckedConstraint(@NonNull T object, @NonNull String propertyName) {
        super(object, propertyName);
    }

    /**
     * Validate if the object view is checked.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();
        final boolean isChecked;

        if (object instanceof RadioGroup) {
            RadioGroup radioGroup = (RadioGroup) object;
            isChecked = View.NO_ID != radioGroup.getCheckedRadioButtonId();
        } else if (object instanceof CompoundButton) {
            CompoundButton radioButton = (CompoundButton) object;
            isChecked = radioButton.isChecked();
        } else {
            throw new UnsupportedException(this, object, propertyName);
        }

        if (!isChecked) {
            violations.add(new Violation(propertyName, object, message, ERROR_CODE_IS_NOT_CHECKED));
        }

        return violations;
    }

    /**
     * Set error message when value violate the constraint
     *
     * @param message String message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
