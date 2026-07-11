package com.nnoi.app.hospital_ms.repository;

import com.nnoi.app.hospital_ms.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PatientRepository {

    private final String PATIENT = "patient";
    private NamedParameterJdbcTemplate namedTemplate;

    public List<Patient> getAllPatients() {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(PATIENT);
        Map<String, Object> params = new HashMap<>();
        List<Patient> patientResultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Patient.class));
        return patientResultSet;
    }

    public Patient getById(Integer patientId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(PATIENT).append(" WHERE patient_id = :patientId");
        Map<String, Object> params = new HashMap<>();
        params.put("patientId", patientId);
        List<Patient> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Patient.class));
        return resultSet.stream()
                .findFirst()
                .orElse(null);
    }
}
