package com.nnoi.app.hospital_ms.util.validator;

import com.nnoi.app.hospital_ms.exceptions.InvalidAppointmentRequestException;
import com.nnoi.app.hospital_ms.model.CreateAppointment;
import com.nnoi.app.hospital_ms.repository.DoctorRepository;
import com.nnoi.app.hospital_ms.repository.PatientRepository;
import com.nnoi.app.hospital_ms.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public final class CreateAppointmentValidator {

    public static final String ROOM_ = "Room ";
    public static final String PATIENT_ = "Patient ";
    public static final String DOCTOR_ = "Doctor ";
    private RoomRepository roomRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    public void validate(CreateAppointment request) {
        List<String> errors = new ArrayList<>();

        if (request == null) {
            throw new InvalidAppointmentRequestException(List.of("request body is required"));
        }

        validateDoctor(request, errors);
        validatePatient(request, errors);
        validateRoom(request, errors);
        validateOtherFields(request, errors);

        if (!errors.isEmpty()) {
            throw new InvalidAppointmentRequestException(errors);
        }
    }

    public void validateDoctor(CreateAppointment request, List<String> errors) {
        Integer doctorId = request.getDoctor_id();
        if (Objects.isNull(doctorId)) {
            errors.add("doctor is required");
        } else {
            if (doctorRepository.getById(doctorId) == null) {
                StringBuilder sb = new StringBuilder(DOCTOR_).append(doctorId).append(" does not exist.");
                errors.add(sb.toString());
            }
        }
    }

    public void validatePatient(CreateAppointment request, List<String> errors) {
        Integer patientId = request.getPatient_id();
        if (Objects.isNull(patientId)) {
            errors.add("patient is required");
        } else {
            if (patientRepository.getById(patientId) == null) {
                StringBuilder sb = new StringBuilder(PATIENT_).append(patientId).append(" does not exist.");
                errors.add(sb.toString());
            }
        }
    }

    public void validateRoom(CreateAppointment request, List<String> errors) {
        Integer roomId = request.getRoomId();
        if (Objects.isNull(roomId)) {
            errors.add("roomId is required");
        } else {
            if (roomRepository.getById(roomId) == null) {
                StringBuilder sb = new StringBuilder(ROOM_).append(roomId).append(" does not exist.");
                errors.add(sb.toString());
            }
            else {
                if (!roomRepository.roomAvailable(roomId)) {
                    StringBuilder sb = new StringBuilder(ROOM_).append(roomId).append(" is not available.");
                    errors.add(sb.toString());
                }
                if (roomRepository.roomInMaintenance(roomId)) {
                    StringBuilder sb = new StringBuilder(ROOM_).append(roomId).append(" is in maintenance.");
                    errors.add(sb.toString());
                }
            }
        }
    }

    public void validateOtherFields(CreateAppointment request, List<String> errors) {
        if (isBlank(request.getDescription())) {
            errors.add("description is required");
        }

        if (Objects.isNull(request.getAppointmentDate())) {
            errors.add("appointmentDate is required");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}