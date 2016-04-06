package com.comuto.validator;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    final List<Field> fields = new ArrayList<>();

    public Validator() {
    }

    public Validator(List<Field> fields) {
        this.fields.addAll(fields);
    }

    public void add(Field field) {
        this.fields.add(field);
    }

    public void addAll(List<Field> fields) {
        this.fields.addAll(fields);
    }

    public List<Violation> validate() {
        final List<Violation> violations = new ArrayList<>();

        for (Field field : fields) {
            violations.addAll(field.validate());
        }

        return violations;
    }

}
