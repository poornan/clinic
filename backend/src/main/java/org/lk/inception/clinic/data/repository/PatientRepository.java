package org.lk.inception.clinic.data.repository;

import org.lk.inception.clinic.data.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, String> {
}
