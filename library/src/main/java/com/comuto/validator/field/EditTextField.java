package com.comuto.validator.field;

import android.widget.EditText;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;

import java.util.List;

public class EditTextField extends Field<EditText, String> {

  public EditTextField(EditText object, List<Constraint<String>> constraints) {
    super(object, constraints);
  }

  @Override
  public String getValue() {
    return object.getText().toString();
  }

}
