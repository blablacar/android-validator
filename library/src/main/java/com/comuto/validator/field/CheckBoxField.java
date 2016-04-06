package com.comuto.validator.field;

import android.widget.CheckBox;

import com.comuto.validator.Constraint;

import java.util.List;

public class CheckBoxField extends CompoundButtonField {
    public CheckBoxField(CheckBox object, List<Constraint<Boolean>> constraints) {
        super(object, constraints);
    }
}
