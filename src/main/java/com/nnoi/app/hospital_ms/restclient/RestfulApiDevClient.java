package com.nnoi.app.hospital_ms.restclient;

import com.nnoi.app.hospital_ms.model.APITest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("https://api.restful-api.dev")
public interface RestfulApiDevClient {

    @GetExchange("/objects")
    public List<APITest> getAllDataTypes();

    @GetExchange("/objects/{id}")
    public APITest getAllDataTypesById(@PathVariable String id);
}
