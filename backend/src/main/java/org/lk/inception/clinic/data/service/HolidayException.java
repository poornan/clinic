package org.lk.inception.clinic.data.service;

import org.lk.inception.clinic.data.model.Visit;

public class HolidayException extends RuntimeException {
    public HolidayException(Visit h) {
        super(h.toString());
    }
}
