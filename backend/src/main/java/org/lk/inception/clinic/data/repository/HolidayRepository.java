package org.lk.inception.clinic.data.repository;

import org.lk.inception.clinic.data.model.Holiday;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HolidayRepository extends CrudRepository<Holiday, String> {
    Optional<Holiday> findByHolidayEquals(LocalDate holidayDate);
}
