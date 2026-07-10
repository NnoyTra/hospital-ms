package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.entity.Doctor;
import com.nnoi.app.hospital_ms.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {

    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctorList() {
        return doctorRepository.getAllDoctors();
    }
}
