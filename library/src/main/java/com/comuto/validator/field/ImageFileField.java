package com.comuto.validator.field;

import com.comuto.validator.Constraint;
import com.comuto.validator.constraint.ImageFileConstraint;

import java.io.File;
import java.util.ArrayList;

public class ImageFileField extends SimpleField<File> {
    private final ImageFileConstraint constraint;

    public ImageFileField(File object) {
        super(object, new ArrayList<Constraint<File>>());

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

    public void setMinSize(double minSize) {
        constraint.setMinSize(minSize);
    }

    public void setMinSizeMessage(String minSizeMessage) {
        constraint.setMinSizeMessage(minSizeMessage);
    }

    public void setMaxSize(double maxSize) {
        constraint.setMaxSize(maxSize);
    }

    public void setMaxSizeMessage(String maxSizeMessage) {
        constraint.setMaxSizeMessage(maxSizeMessage);
    }
}
