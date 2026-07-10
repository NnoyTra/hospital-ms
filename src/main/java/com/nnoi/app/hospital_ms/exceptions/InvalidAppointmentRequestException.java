package com.nnoi.app.hospital_ms.exceptions;

import java.util.List;

public class InvalidAppointmentRequestException extends RuntimeException {

    private final List<String> errors;

    public InvalidAppointmentRequestException(List<String> errors) {
        super("Invalid appointment request: " + String.join(", ", errors));
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}