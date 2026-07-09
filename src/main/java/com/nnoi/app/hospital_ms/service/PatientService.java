package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.model.Patient;
import com.nnoi.app.hospital_ms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired private PatientRepository patientRepository;

    public List<Patient> getPatientList() {
        return patientRepository.getAllPatients();
    }
}
