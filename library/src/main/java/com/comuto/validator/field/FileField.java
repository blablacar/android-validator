package com.comuto.validator.field;

import com.comuto.validator.Constraint;
import com.comuto.validator.constraint.FileConstraint;

import java.io.File;
import java.util.ArrayList;

public class FileField extends SimpleField<File> {
    private final FileConstraint constraint;

    public FileField(File object) {
        super(object, new ArrayList<Constraint<File>>());

        this.constraint = new FileConstraint();
        constraints.add(constraint);
    }

    public void setMinSize(long minSize) {
        constraint.setMinSize(minSize);
    }

    public void setMinSizeMessage(String minSizeMessage) {
        constraint.setMinSizeMessage(minSizeMessage);
    }

    public void setMaxSize(long maxSize) {
        constraint.setMaxSize(maxSize);
    }

    public void setMaxSizeMessage(String maxSizeMessage) {
        constraint.setMaxSizeMessage(maxSizeMessage);
    }

}
