package com.comuto.validator.constraint;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.comuto.validator.Utils.assertEmpty;
import static com.comuto.validator.Utils.assertNotEmpty;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class IsCheckedConstraintTest {

    @SuppressWarnings("ResourceType")
    @Test
    public void isValid() throws Exception {
        RadioGroup radioGroup = new RadioGroup(RuntimeEnvironment.application);
        radioGroup.check(1);
        assertEmpty("is valid with RadioGroup", new IsCheckedConstraint<View>(radioGroup, "radioGroup").validate());

        RadioButton radioButton = new RadioButton(RuntimeEnvironment.application);
        radioButton.setChecked(true);
        assertEmpty("is valid with RadioButton", new IsCheckedConstraint<View>(radioButton, "radioButton").validate());
    }

    @SuppressWarnings("ResourceType")
    @Test
    public void isInValid() throws Exception {
        RadioGroup radioGroup = new RadioGroup(RuntimeEnvironment.application);
        assertNotEmpty("is invalid with RadioGroup", new IsCheckedConstraint<View>(radioGroup, "radioGroup").validate());

        RadioButton radioButton = new RadioButton(RuntimeEnvironment.application);
        assertNotEmpty("is invalid with RadioButton", new IsCheckedConstraint<View>(radioButton, "radioButton").validate());
    }
}