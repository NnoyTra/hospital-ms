package com.nnoi.app.hospital_ms.util.validator;

import com.nnoi.app.hospital_ms.exceptions.InvalidAppointmentRequestException;
import com.nnoi.app.hospital_ms.model.CreateAppointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CreateAppointmentValidator {

    private CreateAppointmentValidator() {
    }

    public static void validate(CreateAppointment request) {
        List<String> errors = new ArrayList<>();

        if (request == null) {
            throw new InvalidAppointmentRequestException(List.of("request body is required"));
        }

        if (isBlank(request.getDescription())) {
            errors.add("description is required");
        }

        if (Objects.isNull(request.getDoctor_id())) {
            errors.add("doctor_id is required");
        }

        if (Objects.isNull(request.getPatient_id())) {
            errors.add("patient_id is required");
        }

        if (Objects.isNull(request.getAppointmentDate())) {
            errors.add("appointmentDate is required");
        }

        if (Objects.isNull(request.getRoomId())) {
            errors.add("roomId is required");
        }

        if (!errors.isEmpty()) {
            throw new InvalidAppointmentRequestException(errors);
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}