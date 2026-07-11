package com.nnoi.app.hospital_ms.kafka;

import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppointmentEventListener {

    public AppointmentEventListener() {}

    @KafkaListener(topics = "appointment-events", groupId = "group-1", containerFactory = "kafkaListenerFactory")
    public void consumeTopicName(ConsumerRecord<String, AppointmentRequest> record) {
        AppointmentRequest value = record.value();
        printLogMessageConsumed(value);
    }

    @KafkaListener(topics = "test", groupId = "group-1", containerFactory = "kafkaListenerFactory")
    public void consumeTest(ConsumerRecord<String, String> record) {
//        printLogMessageConsumed(record);
    }

    private static void printLogMessageConsumed(AppointmentRequest customMessage) {
        log.info("CustomListener-consumeMessage: message parsed. {}", customMessage);
    }
}