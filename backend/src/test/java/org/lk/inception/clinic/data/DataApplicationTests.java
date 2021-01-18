package org.lk.inception.clinic.data;

import org.junit.jupiter.api.Test;
import org.lk.inception.clinic.data.model.Physician;
import org.lk.inception.clinic.data.model.Visit;
import org.lk.inception.clinic.data.service.ClinicService;
import org.lk.inception.clinic.data.service.DuplicateException;
import org.lk.inception.clinic.data.service.HolidayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DataApplicationTests {
    @Autowired
    protected ClinicService clinicService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void getAllVisits() {
        Iterable<Visit> a = clinicService.getAllVisits();
        assertThat(a.iterator().hasNext()).isTrue();
    }

    @Test
    void getVisit() {
        Optional<Visit> a = clinicService.getVisit("Visit1");
        assertThat(a.get().getId()).isEqualTo("Visit1");
    }

    @WithMockUser("USER")
    @Test
    void newVisit() {
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        Physician physician1 = new Physician("Physician1", "Physician1");
        Visit visit1 = new Visit(id, now, physician1, "Reason1");
        Visit a = clinicService.newVisit(visit1);
        assertThat(a.getId()).isEqualTo(id);
    }

    @WithMockUser("USER")
    @Test
    void newVisitDuplicate() {
        LocalDateTime now = LocalDateTime.now();
        Physician physician1 = new Physician("Physician1", "Physician1");
        Visit visit1 = new Visit("Visit1", now, physician1, "Reason1");
        Exception exception = assertThrows(DuplicateException.class, () -> {

            Visit a = clinicService.newVisit(visit1);
        });
        String expectedMessage = visit1.toString();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @WithMockUser("USER")
    @Test
    void visitHoliday() {
        LocalDateTime now = LocalDateTime.of(1989, Month.JANUARY, 01, 00, 00);
        Physician physician1 = new Physician("Physician1", "Physician1");
        Visit visit1 = new Visit("Visit1", now, physician1, "Reason1");
        Exception exception = assertThrows(HolidayException.class, () -> {

            Visit a = clinicService.newVisit(visit1);
        });
        String expectedMessage = visit1.toString();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @WithMockUser("USER")
    @Test
    public void find_login_ok() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/visits")).andDo(
                MockMvcResultHandlers.print()).andExpect(status().isOk());
    }

}
