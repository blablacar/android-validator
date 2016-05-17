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
public class NotEqualsConstraintTest {

    @Test
    public void isValid() throws Exception {
        assertEmpty("is valid with String", new NotEqualsConstraint("test", "not", "string").validate());

        EditText editText = new EditText(RuntimeEnvironment.application);
        editText.setText("test");
        assertEmpty("is valid with EditText", new NotEqualsConstraint(editText, "not", "editText").validate());

        Spinner spinner = new Spinner(RuntimeEnvironment.application);
        spinner.setAdapter(
            new ArrayAdapter<>(RuntimeEnvironment.application, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>() {{
                add("test");
            }}));
        assertEmpty("is valid with Spinner", new NotEqualsConstraint(spinner, "not", "spinner").validate());
    }

    @Test
    public void isInvalid() throws Exception {
        assertNotEmpty("is invalid with String", new NotEqualsConstraint("test", "test", "string").validate());

        EditText editText = new EditText(RuntimeEnvironment.application);
        editText.setText("test");
        assertNotEmpty("is invalid with EditText", new NotEqualsConstraint(editText, "test", "editText").validate());

        Spinner spinner = new Spinner(RuntimeEnvironment.application);
        spinner.setAdapter(
            new ArrayAdapter<>(RuntimeEnvironment.application, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>() {{
                add("test");
            }}));
        assertNotEmpty("is invalid with Spinner", new NotEqualsConstraint(spinner, "test", "spinner").validate());
    }

    @Test(expected = UnsupportedException.class)
    public void testUnsupported() throws Exception {
        new NotEqualsConstraint(new Object(), "test", "test").validate();
    }
}