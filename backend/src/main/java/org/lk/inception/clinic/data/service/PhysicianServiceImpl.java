package org.lk.inception.clinic.data.service;

import org.lk.inception.clinic.data.model.Physician;
import org.lk.inception.clinic.data.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicianServiceImpl implements PhysicianService {
    PhysicianRepository physicianRepository;

    @Autowired
    public PhysicianServiceImpl(PhysicianRepository physicianRepository) {
        this.physicianRepository = physicianRepository;
    }

    @Override
    public Iterable<Physician> getAllPhysician() {
        return physicianRepository.findAll();
    }
}
