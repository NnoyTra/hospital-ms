package com.nnoi.app.hospital_ms.advicecontroller;

import com.nnoi.app.hospital_ms.exceptions.CreatedAppointmentNotPresentException;
import com.nnoi.app.hospital_ms.exceptions.InvalidAppointmentRequestException;
import com.nnoi.app.hospital_ms.exceptions.RoomNotFoundException;
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
public class RoomAdviceController {


    @ExceptionHandler(RoomNotFoundException.class)
    public ProblemDetail handleRoomNotFoundException(
            RoomNotFoundException ex, WebRequest request) {

        log.warn("Created appointment not present: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getHttpStatusCode());
        problemDetail.setTitle("Room Not Found in Records.");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(URI.create(request.getContextPath()));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

}
