package com.nnoi.app.hospital_ms.util.serdes;

import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import org.apache.kafka.common.serialization.Serializer;
import org.hibernate.type.SerializationException;
import tools.jackson.databind.ObjectMapper;

public class AppointmentRequestSerializer implements Serializer<AppointmentRequest> {

    @Override
    public byte[] serialize(String topic, AppointmentRequest appointmentRequest) {
        try {
            return new ObjectMapper().writeValueAsBytes(appointmentRequest);
        } catch (Exception e) {
            throw new SerializationException("Error serializing appointmentRequest", e);
        }
    }
}
