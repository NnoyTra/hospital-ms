package com.nnoi.app.hospital_ms.util.serdes;

import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import org.apache.kafka.common.serialization.Serdes;

public class AppointmentRequestSerde extends Serdes.WrapperSerde<AppointmentRequest> {
    public AppointmentRequestSerde() {
        super(new AppointmentRequestSerializer(), new AppointmentRequestDeserializer());
    }
}
