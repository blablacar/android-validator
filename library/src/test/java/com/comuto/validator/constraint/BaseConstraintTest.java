package com.comuto.validator.constraint;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import org.robolectric.RuntimeEnvironment;

public class BaseConstraintTest {
    protected static final String BLANK = "";
    protected static final String NULL = null;

    protected EditText generateEditText(final String value) {
        EditText editText = new EditText(RuntimeEnvironment.application);
        editText.setText(value);

        return editText;
    }

    protected Spinner generateSpinner(final String value) {
        Spinner spinner = new Spinner(RuntimeEnvironment.application);
        spinner.setAdapter(
            new ArrayAdapter<>(RuntimeEnvironment.application, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>() {{
                add(value);
            }}));

        return spinner;
    }
}
