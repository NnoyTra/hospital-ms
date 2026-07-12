package com.nnoi.app.hospital_ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APITest {
    private String id;
    private String name;
    private DataType data;
}
