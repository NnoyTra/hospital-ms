package com.nnoi.app.hospital_ms.config;

public class KafkaTopicList {

    private KafkaTopicList() {
        // constants holder, not instantiable
    }

    public static final String APPOINTMENT = "appointment-events";
    public static final String APPOINTMENT_WRONG_DATE = "appointment-events-wrong-date";
    public static final String DOCTOR = "doctor-events";
    public static final String PATIENT = "patient-events";
}
