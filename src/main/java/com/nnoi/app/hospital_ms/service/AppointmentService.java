package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.model.Appointment;
import com.nnoi.app.hospital_ms.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointmentList() {
        return appointmentRepository.getAllAppointments();
    }
}
