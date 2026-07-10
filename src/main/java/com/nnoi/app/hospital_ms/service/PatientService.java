package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.entity.Patient;
import com.nnoi.app.hospital_ms.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private PatientRepository patientRepository;

    public List<Patient> getPatientList() {
        return patientRepository.getAllPatients();
    }
}
