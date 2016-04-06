package com.comuto.validator.field;

import com.comuto.validator.constraint.ImageFileConstraint;

import java.io.File;

public class ImageFileField extends FileField {
    private final ImageFileConstraint constraint;

    public ImageFileField(File object) {
        super(object);

        this.constraint = new ImageFileConstraint();
        constraints.add(constraint);
    }

    public void setMinWidth(long minWidth) {
        constraint.setMinWidth(minWidth);
    }

    public void setMaxWidth(long maxWidth) {
        constraint.setMaxWidth(maxWidth);
    }

    public void setMinHeight(long minHeight) {
        constraint.setMinHeight(minHeight);
    }

    public void setMaxHeight(long maxHeight) {
        constraint.setMaxHeight(maxHeight);
    }

    public void setMinWidthMessage(String minWidthMessage) {
        constraint.setMinWidthMessage(minWidthMessage);
    }

    public void setMaxWidthMessage(String maxWidthMessage) {
        constraint.setMaxWidthMessage(maxWidthMessage);
    }

    public void setMinHeightMessage(String minHeightMessage) {
        constraint.setMinHeightMessage(minHeightMessage);
    }

    public void setMaxHeightMessage(String maxHeightMessage) {
        constraint.setMaxHeightMessage(maxHeightMessage);
    }

}
