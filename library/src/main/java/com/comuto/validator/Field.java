package com.comuto.validator;

import java.util.ArrayList;
import java.util.List;

public abstract class Field<T, V> {

    protected final T object;
    protected final List<Constraint<V>> constraints;

    public Field(T object, List<Constraint<V>> constraints) {
        this.object = object;
        this.constraints = constraints;
    }

    public T getObject() {
        return object;
    }

    public abstract V getValue();

    List<Violation> validate() {
        final List<Violation> violations = new ArrayList<>();

        for (Constraint<V> constraint : constraints) {
            violations.addAll(constraint.validate(this));
        }

        return violations;
    }
}
