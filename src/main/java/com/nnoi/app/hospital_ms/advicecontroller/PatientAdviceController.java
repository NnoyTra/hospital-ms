package com.nnoi.app.hospital_ms.advicecontroller;

import com.nnoi.app.hospital_ms.exceptions.CreatedAppointmentNotPresentException;
import com.nnoi.app.hospital_ms.exceptions.InvalidAppointmentRequestException;
import com.nnoi.app.hospital_ms.exceptions.PatientNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class PatientAdviceController {


    @ExceptionHandler(PatientNotFoundException.class)
    public ProblemDetail handleCreatedAppointmentNotPresentException(
            PatientNotFoundException ex, WebRequest request) {

        log.warn("Patient not found in records: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getHttpStatusCode());
        problemDetail.setTitle("Patient not found in records.");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(URI.create(request.getContextPath()));
        problemDetail.setDetail("Make sure createAppointment Object is set in the payload");
        return problemDetail;
    }
}
