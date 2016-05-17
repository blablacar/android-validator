package com.comuto.validator.constraint;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MatchConstraintTest {
    private static final Pattern PATTERN = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
    private static final String VALID = "foo";
    private static final String INVALID = "-";

    @Test
    public void isValid() throws Exception {
        EditText editText = new EditText(RuntimeEnvironment.application);
        editText.setText(VALID);

        assertEmpty("is valid with ediText", new MatchConstraint(editText, PATTERN, "editText").validate());

        Spinner spinner = new Spinner(RuntimeEnvironment.application);
        spinner.setAdapter(
            new ArrayAdapter<>(RuntimeEnvironment.application, android.R.layout.simple_spinner_dropdown_item,
                new ArrayList<String>() {{
                    add(VALID);
                }}));
        assertEmpty("is valid with spinner", new MatchConstraint(spinner, PATTERN, "spinner").validate());

        assertEmpty("is valid with String", new MatchConstraint(VALID, PATTERN, "string").validate());
    }

    @Test
    public void isInValid() throws Exception {
        EditText editText = new EditText(RuntimeEnvironment.application);
        editText.setText(INVALID);

        assertNotEmpty("is invalid with ediText", new MatchConstraint(editText, PATTERN, "editText").validate());

        Spinner spinner = new Spinner(RuntimeEnvironment.application);
        spinner.setAdapter(
            new ArrayAdapter<>(RuntimeEnvironment.application, android.R.layout.simple_spinner_dropdown_item,
                new ArrayList<String>() {{
                    add(INVALID);
                }}));
        assertNotEmpty("is invalid with spinner", new MatchConstraint(spinner, PATTERN, "spinner").validate());

        assertNotEmpty("is invalid with String", new MatchConstraint(INVALID, PATTERN, "string").validate());
    }
}