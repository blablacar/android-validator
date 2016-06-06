package com.comuto.validator.constraint;

import android.support.annotation.NonNull;

import static android.util.Patterns.EMAIL_ADDRESS;

public class EmailConstraint extends MatchConstraint {
    public EmailConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, EMAIL_ADDRESS, propertyName);
    }
}
