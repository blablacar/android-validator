package com.comuto.validator.constraint;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import static junit.framework.Assert.assertNotNull;

public final class NotBlankConstraintTest {

  @Test
  public void testValidateWithNullString() throws Exception {
    List<Violation> violations = new NotBlankConstraint().validate(new TestField(null));

    assertNotNull("Constraint violations not null", violations);
    assertNotNull("Constraint violations not empty", violations.get(0));
  }

  @Test
  public void testValidateWithBlankString() throws Exception {
    List<Violation> violations = new NotBlankConstraint().validate(new TestField(""));

    assertNotNull("Constraint violations not null", violations);
    assertNotNull("Constraint violations not empty", violations.get(0));
  }

  @Test
  public void testValidateWithBlankNonTrimString() throws Exception {
    List<Violation> violations = new NotBlankConstraint().validate(new TestField("    "));

    assertNotNull("Constraint violations not null", violations);
    assertNotNull("Constraint violations not empty", violations.get(0));
  }

  private static final class TestField extends Field<String, String> {
    public TestField(String object) {
      super(object, new ArrayList<Constraint<String>>());
    }

    @Override
    public String getValue() {
      return object;
    }
  }
}