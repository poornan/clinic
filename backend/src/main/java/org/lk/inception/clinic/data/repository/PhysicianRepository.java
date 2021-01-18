package org.lk.inception.clinic.data.repository;

import org.lk.inception.clinic.data.model.Physician;
import org.springframework.data.repository.CrudRepository;

public interface PhysicianRepository extends CrudRepository<Physician, String> {
}
