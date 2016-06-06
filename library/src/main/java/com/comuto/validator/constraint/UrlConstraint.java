package com.comuto.validator.constraint;

import android.support.annotation.NonNull;

import static android.util.Patterns.WEB_URL;

public class UrlConstraint extends MatchConstraint {
    public UrlConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, WEB_URL, propertyName);
    }
}
