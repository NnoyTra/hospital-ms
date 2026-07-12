package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.config.CommonVariables;
import com.nnoi.app.hospital_ms.model.APITest;
import com.nnoi.app.hospital_ms.restclient.RestfulApiDevClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@AllArgsConstructor
public class HospitalController {

    private CommonVariables commonVariables;
    private RestfulApiDevClient restfulApiDevClient;

    @GetMapping
    public String welcome() {
        return commonVariables.getGreetings();
    }

    @GetMapping("/rest-client")
    public List<APITest> restClient() {
        return restfulApiDevClient.getAllDataTypes();
    }

    @GetMapping("/rest-client/{id}")
    public APITest restClient(@PathVariable String id) {
        return restfulApiDevClient.getAllDataTypesById(id);
    }

}
