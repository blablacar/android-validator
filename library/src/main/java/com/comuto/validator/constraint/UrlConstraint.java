package com.comuto.validator.constraint;

import static android.util.Patterns.WEB_URL;

public class UrlConstraint extends MatchConstraint {
    public UrlConstraint(Object object, String propertyName) {
        super(object, WEB_URL, propertyName);
    }
}
