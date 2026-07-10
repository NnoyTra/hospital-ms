package com.nnoi.app.hospital_ms.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomNotFoundException extends RuntimeException{
    private String message;
    private HttpStatus httpStatusCode = HttpStatus.BAD_REQUEST;
}
