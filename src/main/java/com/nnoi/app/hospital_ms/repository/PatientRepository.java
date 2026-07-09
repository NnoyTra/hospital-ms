package com.nnoi.app.hospital_ms.repository;

import com.nnoi.app.hospital_ms.model.Doctor;
import com.nnoi.app.hospital_ms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PatientRepository {

    private final String PATIENT = "patient";
    @Autowired private NamedParameterJdbcTemplate namedTemplate;

    public List<Patient> getAllPatients() {

        StringBuilder sql = new StringBuilder("SELECT * FROM " + PATIENT);
        Map<String, String> params = new HashMap<>();
        List<Patient> patientResultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Patient.class));

        return patientResultSet;
    }
}
