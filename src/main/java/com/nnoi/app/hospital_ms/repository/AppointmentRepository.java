package com.nnoi.app.hospital_ms.repository;

import com.nnoi.app.hospital_ms.model.Appointment;
import com.nnoi.app.hospital_ms.model.Room;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AppointmentRepository {

    private final String APPOINTMENT = "appointment";
    private NamedParameterJdbcTemplate namedTemplate;

    public List<Appointment> getAllAppointments() {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + APPOINTMENT);
        Map<String, String> params = new HashMap<>();
        List<Appointment> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Appointment.class));
        return resultSet;
    }
}
