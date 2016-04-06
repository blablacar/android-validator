package com.comuto.validator;

import java.util.List;

public abstract class Constraint<V> {

  protected abstract List<Violation> validate(Field<?, V> field);

  public String getName() {
    return this.getClass().getSimpleName();
  }

}
