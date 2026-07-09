package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.model.Doctor;
import com.nnoi.app.hospital_ms.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired private DoctorService doctorService;

    @GetMapping
    List<Doctor> getDoctors() {
        return doctorService.getDoctorList();
    }
}
