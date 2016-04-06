package com.comuto.validator;

import com.comuto.validator.field.FileField;

import java.io.File;

public class FileValidator extends Validator {

  private final FileField fileField;

  public FileValidator(File file) {
    super();

    this.fileField = new FileField(file);
    add(fileField);
  }

  public void setMinSize(long minSize) {
    this.fileField.setMinSize(minSize);
  }

  public void setMinSizeMessage(String minSizeMessage) {
    this.fileField.setMinSizeMessage(minSizeMessage);
  }

  public void setMaxSize(long maxSize) {
    this.fileField.setMaxSize(maxSize);
  }

  public void setMaxSizeMessage(String maxSizeMessage) {
    this.fileField.setMaxSizeMessage(maxSizeMessage);
  }

}
