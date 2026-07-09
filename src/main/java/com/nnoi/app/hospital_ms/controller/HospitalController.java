package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.model.Doctor;
import com.nnoi.app.hospital_ms.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@AllArgsConstructor
public class HospitalController {

    @GetMapping
    public String welcome() {
        return "Welcome to nNoi hospital.";
    }

}
