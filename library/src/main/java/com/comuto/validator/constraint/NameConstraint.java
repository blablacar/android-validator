package com.comuto.validator.constraint;

import java.util.regex.Pattern;

public class NameConstraint extends MatchConstraint {
    public static final Pattern PATTERN =
        Pattern.compile("^[\\p{L} .'-]+$", Pattern.CASE_INSENSITIVE);

    public NameConstraint(Object object, String propertyName) {
        super(object, PATTERN, propertyName);
    }
}
