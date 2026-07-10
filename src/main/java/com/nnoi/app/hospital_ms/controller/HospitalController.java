package com.nnoi.app.hospital_ms.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
@AllArgsConstructor
public class HospitalController {

    @GetMapping
    public String welcome() {
        return "Welcome to nNoi hospital.";
    }

}
