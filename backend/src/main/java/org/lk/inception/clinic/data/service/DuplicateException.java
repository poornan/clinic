package org.lk.inception.clinic.data.service;

import org.lk.inception.clinic.data.model.Visit;

public class DuplicateException extends RuntimeException {
    public DuplicateException(Visit v) {
        super(v.toString());
    }
}
