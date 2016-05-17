package com.comuto.validator.constraint;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.comuto.validator.UnsupportedException;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class NotBlankConstraintTest {

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new NotBlankConstraint("test", "string").validate());

        EditText editText = new EditText(RuntimeEnvironment.application);
        editText.setText("test");
        assertEmpty("is valid with EditText", new NotBlankConstraint(editText, "editText").validate());

        Spinner spinner = new Spinner(RuntimeEnvironment.application);
        spinner.setAdapter(
            new ArrayAdapter<>(RuntimeEnvironment.application, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>() {{
                add("test");
            }}));
        assertEmpty("is valid with Spinner", new NotBlankConstraint(spinner, "spinner").validate());
    }

    @Test
    public void isInvalid() throws Exception {
        assertNotEmpty("is invalid with String", new NotBlankConstraint("", "string").validate());

        EditText editText = new EditText(RuntimeEnvironment.application);
        assertNotEmpty("is invalid with EditText", new NotBlankConstraint(editText, "editText").validate());

        Spinner spinner = new Spinner(RuntimeEnvironment.application);
        spinner.setAdapter(
            new ArrayAdapter<>(RuntimeEnvironment.application, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>() {{
                add("");
            }}));
        assertNotEmpty("is invalid with Spinner", new NotBlankConstraint(spinner, "string").validate());
    }

    @Test(expected = UnsupportedException.class)
    public void testUnsupported() throws Exception {
        new NotBlankConstraint(new Object(), "test").validate();
    }
}