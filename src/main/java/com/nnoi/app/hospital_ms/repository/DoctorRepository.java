package com.nnoi.app.hospital_ms.repository;

import com.nnoi.app.hospital_ms.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.CompletableFuture;

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
}
