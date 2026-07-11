package com.nnoi.app.hospital_ms.util.serdes;

import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import org.apache.kafka.common.serialization.Deserializer;
import org.hibernate.type.SerializationException;
import tools.jackson.databind.ObjectMapper;

public class AppointmentRequestDeserializer implements Deserializer<AppointmentRequest> {

     @Override
    public AppointmentRequest deserialize(String s, byte[] bytes) {
         try {
             return new ObjectMapper().readValue(bytes, AppointmentRequest.class);
         } catch (Exception e) {
             throw new SerializationException("Error deserializing appointmentRequest", e);
         }
    }
}
