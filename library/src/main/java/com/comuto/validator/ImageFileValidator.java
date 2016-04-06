package com.comuto.validator;

import com.comuto.validator.field.ImageFileField;

import java.io.File;

public class ImageFileValidator extends Validator {

    private final ImageFileField imageFileField;

    public ImageFileValidator(File file) {
        super();

        this.imageFileField = new ImageFileField(file);
        add(imageFileField);
    }

    public void setMaxHeightMessage(String maxHeightMessage) {
        imageFileField.setMaxHeightMessage(maxHeightMessage);
    }

    public void setMinSize(long minSize) {
        imageFileField.setMinSize(minSize);
    }

    public void setMinSizeMessage(String minSizeMessage) {
        imageFileField.setMinSizeMessage(minSizeMessage);
    }

    public void setMaxSize(long maxSize) {
        imageFileField.setMaxSize(maxSize);
    }

    public void setMaxSizeMessage(String maxSizeMessage) {
        imageFileField.setMaxSizeMessage(maxSizeMessage);
    }

    public void setMinWidth(long minWidth) {
        imageFileField.setMinWidth(minWidth);
    }

    public void setMaxWidth(long maxWidth) {
        imageFileField.setMaxWidth(maxWidth);
    }

    public void setMinHeight(long minHeight) {
        imageFileField.setMinHeight(minHeight);
    }

    public void setMaxHeight(long maxHeight) {
        imageFileField.setMaxHeight(maxHeight);
    }

    public void setMinWidthMessage(String minWidthMessage) {
        imageFileField.setMinWidthMessage(minWidthMessage);
    }

    public void setMaxWidthMessage(String maxWidthMessage) {
        imageFileField.setMaxWidthMessage(maxWidthMessage);
    }

    public void setMinHeightMessage(String minHeightMessage) {
        imageFileField.setMinHeightMessage(minHeightMessage);
    }

}
