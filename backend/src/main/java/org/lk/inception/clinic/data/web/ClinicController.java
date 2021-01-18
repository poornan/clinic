package org.lk.inception.clinic.data.web;

import org.lk.inception.clinic.data.model.Visit;
import org.lk.inception.clinic.data.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClinicController {
    private final ClinicService clinicService;

    private final RestTemplate template = new RestTemplate();

    private String billingURL = "http://localhost:8080/billing";

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return clinicService.getAllVisits();
    }

    @GetMapping("/visits/{id}")
    public Visit getOneVisit(@PathVariable String id) {
        return clinicService.getVisit(id).orElseThrow(
                () -> new PatientNotFoundException(id));
    }

    @PostMapping("/visits")
    public Visit createVisit(@RequestBody Visit visit) {
        Visit a = clinicService.newVisit(visit);
        extracted(visit);
        return a;
    }

    private void extracted(Visit visit) {
        try {//TODO

            String status = template.getForObject(billingURL, String.class);
            if (status.equalsIgnoreCase("success")) {
                template.patchForObject(billingURL, visit, String.class);
            }
        } catch (Throwable e) {
        }
    }

    @PutMapping("/visits/{id}")//TODO
    public Visit updateVisit(@RequestBody Visit visit,
                             @PathVariable String id) {
        return clinicService.updateVisit(visit, id);
    }

    @DeleteMapping("/visits/{id}")
    public void deleteVisit(@PathVariable String id) {
        clinicService.deleteVisit(id);
    }
}