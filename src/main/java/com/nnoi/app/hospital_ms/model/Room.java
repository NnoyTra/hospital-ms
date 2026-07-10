package com.nnoi.app.hospital_ms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private int roomId;
    private String number;
    private Boolean isEquipped;
    private Boolean inMaintenance;
    private Boolean isReserved;
}
