package com.comuto.validator.field;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;

import java.util.List;

public abstract class SimpleField<T> extends Field<T, T> {
  public SimpleField(T object, List<Constraint<T>> constraints) {
    super(object, constraints);
  }

  @Override
  public T getObject() {
    return object;
  }

  @Override
  public T getValue() {
    return object;
  }
}
