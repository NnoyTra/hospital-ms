package com.nnoi.app.hospital_ms.repository;

import com.nnoi.app.hospital_ms.entity.Doctor;
import com.nnoi.app.hospital_ms.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@AllArgsConstructor
public class DoctorRepository {

    private final String DOCTOR = "doctor";
    private NamedParameterJdbcTemplate namedTemplate;

    public List<Doctor> getAllDoctors() {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + DOCTOR);
        Map<String, String> params = new HashMap<>();
        List<Doctor> doctorResultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Doctor.class));
        return doctorResultSet;
    }

    public Doctor getById(Integer doctorId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(DOCTOR).append(" WHERE doctor_id = :doctorId");
        Map<String, Object> params = new HashMap<>();
        params.put("doctorId", doctorId);
        List<Doctor> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Doctor.class));
        return resultSet.stream()
                .findFirst()
                .orElse(null);
    }
}
