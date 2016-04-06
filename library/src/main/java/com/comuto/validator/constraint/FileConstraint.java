package com.comuto.validator.constraint;

import com.comuto.validator.Constraint;
import com.comuto.validator.Field;
import com.comuto.validator.Violation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileConstraint extends Constraint<File> {

  public static final String ERROR_CODE_TOO_SMALL = "ERROR_CODE_TOO_SMALL";
  public static final String ERROR_CODE_TOO_LARGE = "ERROR_CODE_TOO_LARGE";

  private static final String DEFAULT_MIN_SIZE_TEXT = "The file is too small.";
  private static final String DEFAULT_MAX_SIZE_TEXT = "The file is too large.";

  private long minSize = -1;
  private long maxSize = -1;

  private String minSizeMessage = DEFAULT_MIN_SIZE_TEXT;
  private String maxSizeMessage = DEFAULT_MAX_SIZE_TEXT;

  public FileConstraint() {
  }

  public void setMinSize(long minSize) {
    this.minSize = minSize;
  }

  public void setMinSizeMessage(String minSizeMessage) {
    this.minSizeMessage = minSizeMessage;
  }

  public void setMaxSize(long maxSize) {
    this.maxSize = maxSize;
  }

  public void setMaxSizeMessage(String maxSizeMessage) {
    this.maxSizeMessage = maxSizeMessage;
  }

  @Override
  public List<Violation> validate(Field<?, File> field) {
    final List<Violation> violations = new ArrayList<>();
    final long size = field.getValue().length();

    if(-1 != minSize && size <= minSize) {
      violations.add(new Violation(minSizeMessage, ERROR_CODE_TOO_SMALL, field));
    }

    if(-1 != maxSize && size >= maxSize) {
      violations.add(new Violation(maxSizeMessage, ERROR_CODE_TOO_LARGE, field));
    }

    return violations;
  }
}
