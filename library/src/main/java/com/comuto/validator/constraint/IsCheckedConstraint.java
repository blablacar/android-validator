package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.HashSet;
import java.util.Set;

public class IsCheckedConstraint<T extends View> extends Constraint<T> {
    public static final String ERROR_CODE_IS_NOT_CHECKED = "ERROR_CODE_IS_NOT_CHECKED";

    protected String message = "This should have at least one checked choice.";

    public IsCheckedConstraint(T object, String propertyName) {
        super(object, propertyName);
    }

    @NonNull
    @Override
    public Set<Violation> validate() throws UnsupportedException {
        final Set<Violation> violations = new HashSet<>();
        final boolean isChecked;

        if (object instanceof RadioGroup) {
            RadioGroup radioGroup = (RadioGroup) object;
            isChecked = View.NO_ID != radioGroup.getCheckedRadioButtonId();
        } else if (object instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) object;
            isChecked = radioButton.isChecked();
        } else {
            throw new UnsupportedException(this, object, propertyName);
        }

        if (!isChecked) {
            violations.add(new Violation(propertyName, object, message, ERROR_CODE_IS_NOT_CHECKED));
        }

        return violations;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
