package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.entity.Appointment;
import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import com.nnoi.app.hospital_ms.model.AppointmentResponse;
import com.nnoi.app.hospital_ms.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.publishAppointment(appointmentRequest);
        AppointmentResponse appResponse = new AppointmentResponse("Processing Message", HttpStatus.OK);
        return ResponseEntity.ok(appResponse);
    }
}
