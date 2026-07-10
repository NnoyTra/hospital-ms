package com.nnoi.app.hospital_ms.advicecontroller;

import com.nnoi.app.hospital_ms.exceptions.CreatedAppointmentNotPresentException;
import com.nnoi.app.hospital_ms.exceptions.InvalidAppointmentRequestException;
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
public class AppointmentAdviceController {


    @ExceptionHandler(CreatedAppointmentNotPresentException.class)
    public ProblemDetail handleCreatedAppointmentNotPresent(
            CreatedAppointmentNotPresentException ex, WebRequest request) {

        log.warn("Created appointment not present: {}", "Appointment Not Found In Payload");

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Appointment Not Found In Payload");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(URI.create(request.getContextPath()));
        problemDetail.setDetail("Make sure createAppointment Object is set in the payload");
        return problemDetail;
    }

    @ExceptionHandler(InvalidAppointmentRequestException.class)
    public ProblemDetail handleInvalidAppointmentRequest(InvalidAppointmentRequestException ex) {

        log.warn("Invalid appointment request: {}", ex.getErrors());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "One or more fields are invalid");
        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", ex.getErrors());

        return problemDetail;
    }
}
