package com.nnoi.app.hospital_ms.producer;

import com.nnoi.app.hospital_ms.config.KafkaTopicList;
import com.nnoi.app.hospital_ms.entity.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AppointmentEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(AppointmentEventPublisher.class);

    private final KafkaTemplate<String, Appointment> kafkaTemplate;

    public AppointmentEventPublisher(KafkaTemplate<String, Appointment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes an Appointment event keyed by the appointment id.
     * Using the id as the key ensures all events for the same
     * appointment land on the same partition, preserving order.
     */
    public void publish(String key, Appointment appointment) {
        String topic = KafkaTopicList.APPOINTMENT;
        CompletableFuture<SendResult<String, Appointment>> future =
                kafkaTemplate.send(topic, key, appointment);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Published appointment [{}] to topic [{}] partition [{}] offset [{}]",
                        key, topic,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Failed to publish appointment [{}] to topic [{}]", key, topic, ex);
            }
        });
    }
}