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

public class PaymentInformationFormValidator extends Validator {
    private static final String PROPERTY_CARD_TYPE = "cardType";
    private static final String PROPERTY_CARD_NUMBER = "cardNumber";
    private static final String PROPERTY_EXPIRATION_DATE = "expirationDate";
    private static final String PROPERTY_SECURITY_CODE = "securityCode";

    private final TextInputEditText cardNumber;
    private final TextInputEditText expirationDate;
    private final TextInputEditText securityCode;
    private final TextView cardTypeError;

    public PaymentInformationFormValidator(AppCompatSpinner cardType, TextInputEditText cardNumber,
        TextInputEditText expirationDate, TextInputEditText securityCode, TextView cardTypeError) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.cardTypeError = cardTypeError;

        add(new NotEqualsConstraint(cardType, "Card type", PROPERTY_CARD_TYPE));
        add(new NotBlankConstraint(cardNumber, PROPERTY_CARD_NUMBER));
        add(new NotBlankConstraint(expirationDate, PROPERTY_EXPIRATION_DATE));
        add(new NotBlankConstraint(securityCode, PROPERTY_SECURITY_CODE));
    }

    public boolean isValidOrDisplayErrors() {
        cardTypeError.setVisibility(View.INVISIBLE);
        Set<Violation> violations = super.validate();

        if (!violations.isEmpty()) {
            for (Violation violation : violations) {
                if (PROPERTY_CARD_NUMBER.equals(violation.getPropertyName())) {
                    ((TextInputLayout) cardNumber.getParent()).setError(violation.getMessage());
                } else if (PROPERTY_EXPIRATION_DATE.equals(violation.getPropertyName())) {
                    ((TextInputLayout) expirationDate.getParent()).setError(violation.getMessage());
                } else if (PROPERTY_SECURITY_CODE.equals(violation.getPropertyName())) {
                    ((TextInputLayout) securityCode.getParent()).setError(violation.getMessage());
                } else if (PROPERTY_CARD_TYPE.equals(violation.getPropertyName())) {
                    cardTypeError.setText(violation.getMessage());
                    cardTypeError.setVisibility(View.VISIBLE);
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
