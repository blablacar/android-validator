package com.comuto.validator.constraint;

import android.support.annotation.NonNull;
import com.comuto.validator.UnsupportedException;
import com.comuto.validator.Violation;
import java.util.Set;

public abstract class Constraint<T> {
    protected final T object;
    protected final String propertyName;

    public Constraint(@NonNull T object, @NonNull  String propertyName) {
        this.object = object;
        this.propertyName = propertyName;
    }

    /**
     * Implement the validation logic.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @NonNull
    public abstract Set<Violation> validate() throws UnsupportedException;
}
