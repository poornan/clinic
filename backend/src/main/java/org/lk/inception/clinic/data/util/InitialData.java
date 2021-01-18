package org.lk.inception.clinic.data.util;

import org.lk.inception.clinic.data.model.Holiday;
import org.lk.inception.clinic.data.model.Patient;
import org.lk.inception.clinic.data.model.Physician;
import org.lk.inception.clinic.data.model.Visit;
import org.lk.inception.clinic.data.repository.HolidayRepository;
import org.lk.inception.clinic.data.repository.PatientRepository;
import org.lk.inception.clinic.data.repository.PhysicianRepository;
import org.lk.inception.clinic.data.repository.VisitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Configuration
public class InitialData {
    LocalDateTime now = LocalDateTime.now();
    Physician physician1 = new Physician("Physician1", "Physician1");
    Physician physician2 = new Physician("Physician2", "Physician2");
    Visit visit1 = new Visit("Visit1", now, physician1, "Reason1");
    Patient patient1 = new Patient("Patient1", "Patient1", 30, 'M');
    Patient patient2 = new Patient("Patient2", "Patient2", 31, 'F');
    LocalDate day = LocalDate.of(1989, Month.JANUARY, 1);
    Holiday holiday = new Holiday("New year", day);

    @Bean
    CommandLineRunner initPatientData(PatientRepository repository) {
        LocalDateTime a = LocalDateTime.now();
        return args -> {
            repository.save(patient1);
            repository.save(patient2);
        };
    }

    @Bean
    CommandLineRunner initHoliday(HolidayRepository repository) {
        return args -> repository.save(holiday);
    }

    @Bean
    CommandLineRunner initPhysicianData(PhysicianRepository repository) {
        return args -> {
            repository.save(physician1);
            repository.save(physician2);
        };
    }

    @Bean
    CommandLineRunner initVisitData(VisitRepository repository) {
        return args -> repository.save(visit1);
    }
}
