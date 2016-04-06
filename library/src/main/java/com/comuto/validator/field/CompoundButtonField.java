package com.comuto.validator.field;

import android.widget.CompoundButton;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;

import java.util.List;

public class CompoundButtonField extends Field<CompoundButton, Boolean> {
    public CompoundButtonField(CompoundButton object, List<Constraint<Boolean>> constraints) {
        super(object, constraints);
    }

    @Override
    public Boolean getValue() {
        return object.isChecked();
    }
}
