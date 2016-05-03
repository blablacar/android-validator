package com.comuto.validator.sample;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.TextView;
import com.comuto.validator.Validator;
import com.comuto.validator.Violation;
import com.comuto.validator.constraint.NotBlankConstraint;
import com.comuto.validator.constraint.NotEqualsConstraint;
import java.util.Set;

public class AddressFormValidator extends Validator {
    private static final String PROPERTY_ADDRESS = "address";
    private static final String PROPERTY_CITY = "city";
    private static final String PROPERTY_STATE = "state";
    private static final String PROPERTY_ZIP_CODE = "zipCode";
    private static final String PROPERTY_COUNTRY = "country";

    private final TextInputEditText address;
    private final TextInputEditText city;
    private final TextInputEditText zipCode;
    private final TextView stateError;
    private final TextView countryError;

    public AddressFormValidator(TextInputEditText address, TextInputEditText city, AppCompatSpinner state,
        TextInputEditText zipCode, AppCompatSpinner country, TextView stateError, TextView countryError) {
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.stateError = stateError;
        this.countryError = countryError;

        add(new NotBlankConstraint(address, PROPERTY_ADDRESS));
        add(new NotBlankConstraint(city, PROPERTY_CITY));
        add(new NotEqualsConstraint(state, "State", PROPERTY_STATE));
        add(new NotBlankConstraint(zipCode, PROPERTY_ZIP_CODE));
        add(new NotEqualsConstraint(country, "Country", PROPERTY_COUNTRY));
    }

    public boolean isValidOrDisplayErrors() {
        setTextInputLayoutError(address, "");
        setTextInputLayoutError(city, "");
        setTextInputLayoutError(zipCode, "");
        stateError.setVisibility(View.INVISIBLE);
        countryError.setVisibility(View.INVISIBLE);

        Set<Violation> violations = super.validate();

        if (!violations.isEmpty()) {
            for (Violation violation : violations) {
                if (PROPERTY_ADDRESS.equals(violation.getPropertyName())) {
                    setTextInputLayoutError(address, violation.getMessage());
                } else if (PROPERTY_CITY.equals(violation.getPropertyName())) {
                    ((TextInputLayout) city.getParent()).setError(violation.getMessage());
                    setTextInputLayoutError(city, violation.getMessage());
                } else if (PROPERTY_STATE.equals(violation.getPropertyName())) {
                    stateError.setVisibility(View.VISIBLE);
                    stateError.setText(violation.getMessage());
                } else if (PROPERTY_ZIP_CODE.equals(violation.getPropertyName())) {
                    setTextInputLayoutError(zipCode, violation.getMessage());
                } else if (PROPERTY_COUNTRY.equals(violation.getPropertyName())) {
                    countryError.setVisibility(View.VISIBLE);
                    countryError.setText(violation.getMessage());
                }
            }

            return false;
        } else {
            return true;
        }
    }

    private void setTextInputLayoutError(TextInputEditText editText, String errorMessage) {
        ((TextInputLayout) editText.getParent()).setError(errorMessage);
    }
}
