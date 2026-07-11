package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.config.CommonVariables;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
@AllArgsConstructor
public class HospitalController {

    private CommonVariables commonVariables;

    @GetMapping
    public String welcome() {
        return commonVariables.getGreetings();
    }

}
