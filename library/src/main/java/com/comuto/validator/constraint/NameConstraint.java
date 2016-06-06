package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import java.util.regex.Pattern;

public class NameConstraint extends MatchConstraint {
    public static final Pattern PATTERN =
        Pattern.compile("^[\\p{L} .'-]+$", Pattern.CASE_INSENSITIVE);

    public NameConstraint(@NonNull Object object, @NonNull String propertyName) {
        super(object, PATTERN, propertyName);
    }
}
