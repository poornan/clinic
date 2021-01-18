package org.lk.inception.clinic.data.web;

class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String id) {
        super("Could not find patient with ID: " + id);
    }
}
