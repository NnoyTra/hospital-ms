package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.model.Doctor;
import com.nnoi.app.hospital_ms.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired private DoctorRepository doctorRepository;

    public List<Doctor> getDoctorList() {
        return doctorRepository.getAllDoctors();
    }
}
