package com.comuto.validator.constraint;

import static android.util.Patterns.EMAIL_ADDRESS;

public class EmailConstraint extends MatchConstraint {
    public EmailConstraint(Object object, String propertyName) {
        super(object, EMAIL_ADDRESS, propertyName);
    }
}
