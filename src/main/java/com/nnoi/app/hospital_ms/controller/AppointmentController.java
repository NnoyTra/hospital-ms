package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.model.Appointment;
import com.nnoi.app.hospital_ms.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private AppointmentService appointmentService;

    @GetMapping
    List<Appointment> getDoctors() {
        return appointmentService.getAppointmentList();
    }
}
