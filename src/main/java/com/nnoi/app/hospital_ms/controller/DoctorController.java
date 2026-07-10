package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.entity.Doctor;
import com.nnoi.app.hospital_ms.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

    private DoctorService doctorService;

    @GetMapping
    List<Doctor> getDoctors() {
        return doctorService.getDoctorList();
    }
}
