package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.entity.Patient;
import com.nnoi.app.hospital_ms.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @GetMapping
    List<Patient> getPatients() {
        return patientService.getPatientList();
    }
}
