package org.lk.inception.clinic.data.web;

import org.lk.inception.clinic.data.model.Physician;
import org.lk.inception.clinic.data.service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhysicianController {
    private final PhysicianService physicianService;

    @Autowired
    public PhysicianController(PhysicianService physicianService) {
        this.physicianService = physicianService;
    }

    @GetMapping("/physicians")
    public Iterable<Physician> getVisits() {
        return physicianService.getAllPhysician();
    }


}