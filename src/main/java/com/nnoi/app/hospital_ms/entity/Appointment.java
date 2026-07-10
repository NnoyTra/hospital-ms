package com.nnoi.app.hospital_ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id private int appointmentId;
    private String description;
    private Integer doctor_id;
    private Integer patient_id;
    private LocalDate appointmentDate;
    private Integer roomId;
}
