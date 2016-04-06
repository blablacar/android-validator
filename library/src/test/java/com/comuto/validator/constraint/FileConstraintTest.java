package com.comuto.validator.constraint;

import com.comuto.validator.Violation;
import com.comuto.validator.field.FileField;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FileConstraintTest {

  @Test
  public void testValidateWithNoConstraint() throws Exception {
    List<Violation> violations = new FileConstraint().validate(new FileField(new File("")));

    assertNotNull("Constraint violations not null", violations);
    assertTrue("Constraint violations empty", violations.isEmpty());
  }

  @Test
  public void testValidateWithMinConstraint() throws Exception {
    FileConstraint constraint = new FileConstraint();
    constraint.setMinSize(10);
    constraint.setMinSizeMessage("MIN_SIZE");
    List<Violation> violations = constraint.validate(new FileField(new File("")));

    assertEquals("Constraint violations empty", violations.size(), 1);
    assertEquals("Constraint first violation message:", violations.get(0).getMessage(), "MIN_SIZE");
  }

  @Test
  public void testValidateWithMaxConstraint() throws Exception {
    FileConstraint constraint = new FileConstraint();
    constraint.setMaxSize(0);
    constraint.setMaxSizeMessage("MAX_SIZE");
    List<Violation> violations = constraint.validate(new FileField(new File("")));

    assertEquals("Constraint violations empty", violations.size(), 1);
    assertEquals("Constraint first violation message:", violations.get(0).getMessage(), "MAX_SIZE");
  }
}