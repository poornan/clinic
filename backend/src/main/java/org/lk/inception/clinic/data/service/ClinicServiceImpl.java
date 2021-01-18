package org.lk.inception.clinic.data.service;

import org.lk.inception.clinic.data.model.Visit;
import org.lk.inception.clinic.data.repository.HolidayRepository;
import org.lk.inception.clinic.data.repository.PatientRepository;
import org.lk.inception.clinic.data.repository.PhysicianRepository;
import org.lk.inception.clinic.data.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {
    HolidayRepository holidayRepository;
    PatientRepository patientRepository;
    PhysicianRepository physicianRepository;
    VisitRepository visitRepository;

    @Autowired
    public ClinicServiceImpl(HolidayRepository holidayRepository,
                             PatientRepository patientRepository,
                             PhysicianRepository physicianRepository,
                             VisitRepository visitRepository) {
        this.holidayRepository = holidayRepository;
        this.patientRepository = patientRepository;
        this.physicianRepository = physicianRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public Iterable<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public Optional<Visit> getVisit(String id) {
        return visitRepository.findById(id);
    }

    @Override
    public Visit newVisit(Visit visit) {
        holidayRepository.findByHolidayEquals(
                visit.getVisitDatetime().toLocalDate()).ifPresent(h -> {
            throw new HolidayException(visit);
        });
        visitRepository.findById(visit.getId()).ifPresent(v -> {
            throw new DuplicateException(visit);
        });
        return visitRepository.save(visit);//TODO
    }

    @Override
    public Visit updateVisit(Visit visit, String id) {
        holidayRepository.findByHolidayEquals(
                visit.getVisitDatetime().toLocalDate()).ifPresent(h -> {
            throw new HolidayException(visit);
        });
        visitRepository.findById(visit.getId()).ifPresent(v -> {
            throw new DuplicateException(visit);
        });
        return visitRepository.save(visit);
    }

    @Override
    public void deleteVisit(String id) {
        visitRepository.deleteById(id);
    }


}
