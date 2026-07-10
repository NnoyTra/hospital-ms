package com.nnoi.app.hospital_ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointment {

    private String description;
    private Integer doctor_id;
    private Integer patient_id;
    private LocalDate appointmentDate;
    private Integer roomId;
}
