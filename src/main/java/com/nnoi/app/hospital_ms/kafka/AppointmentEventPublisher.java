package com.nnoi.app.hospital_ms.kafka;

import com.nnoi.app.hospital_ms.config.KafkaTopicList;
import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@AllArgsConstructor
public class AppointmentEventPublisher {

    private final KafkaTemplate<String, AppointmentRequest> kafkaTemplate;

    /**
     * Publishes an Appointment event keyed by the appointment id.
     * Using the id as the key ensures all events for the same
     * appointment land on the same partition, preserving order.
     */
    public void publish(String key, AppointmentRequest appointmentRequest) {
        String topic = KafkaTopicList.APPOINTMENT;
        log.info("Preparing to publish event [{}] with appointment [{}]", topic, appointmentRequest);
        CompletableFuture<SendResult<String, AppointmentRequest>> future =
                kafkaTemplate.send(topic, key, appointmentRequest);
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