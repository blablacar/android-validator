package com.comuto.validator.field;

import android.widget.RadioButton;

import com.comuto.validator.Constraint;

import java.util.List;

public class RadioButtonField extends CompoundButtonField {
    public RadioButtonField(RadioButton object, List<Constraint<Boolean>> constraints) {
        super(object, constraints);
    }
}
