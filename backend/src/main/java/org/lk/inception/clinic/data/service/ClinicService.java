package org.lk.inception.clinic.data.service;

import org.lk.inception.clinic.data.model.Visit;

import java.util.Optional;

public interface ClinicService {
    Iterable<Visit> getAllVisits();

    Optional<Visit> getVisit(String id);

    Visit newVisit(Visit visit);

    Visit updateVisit(Visit visit, String id);

    void deleteVisit(String id);

}
