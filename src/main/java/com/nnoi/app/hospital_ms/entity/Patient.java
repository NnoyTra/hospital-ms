package com.nnoi.app.hospital_ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    private int patientId;
    private String firstname;
    private String lastname;
    private LocalDate dob;
    private LocalDate dateOfIntern;
    private LocalDate dateOfRelease;
    private Integer attendingDoctorId;
}
