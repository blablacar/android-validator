package com.comuto.validator;

import com.comuto.validator.field.FileField;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ViolationTest {
  private static final String MESSAGE = "MESSAGE";
  private static final String ERROR_CODE = "ERROR_CODE";
  private static final FileField FILE_FIELD = new FileField(null);

  @Test
  public void testValues() throws Exception {
    Violation violation = new Violation(MESSAGE, ERROR_CODE, FILE_FIELD);

    assertEquals("Message", MESSAGE, violation.getMessage());
    assertEquals("Error code", ERROR_CODE, violation.getCode());
    assertEquals("File field", FILE_FIELD, violation.getField());
  }
}