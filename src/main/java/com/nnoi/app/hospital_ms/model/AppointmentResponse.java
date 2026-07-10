package com.nnoi.app.hospital_ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {

    private String message;
    private HttpStatus httpStatusCode;
}
