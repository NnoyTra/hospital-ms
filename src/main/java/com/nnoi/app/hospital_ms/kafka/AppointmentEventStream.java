package com.nnoi.app.hospital_ms.kafka;

import com.nnoi.app.hospital_ms.config.KafkaTopicList;
import com.nnoi.app.hospital_ms.model.AppointmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JacksonJsonSerde;

import java.time.LocalDate;

@Configuration
@EnableKafkaStreams
@Slf4j
public class AppointmentEventStream {

    /*
        create Bean
        1-) read the topic
        2-) process filter
        3-) write to destination
     */
    @Bean
    public KStream<String, AppointmentRequest> appointmentsWithWrongDate(StreamsBuilder builder) {
        var appointmentRequestSerde = new JacksonJsonSerde<>(AppointmentRequest.class);
        KStream<String, AppointmentRequest> appStream = builder.stream(KafkaTopicList.APPOINTMENT, Consumed.with(Serdes.String(), appointmentRequestSerde));
        KStream<String, AppointmentRequest> appointmentStream = appStream.filter((key, appointmentRequest) ->
                        appointmentRequest.getCreateAppointment().getAppointmentDate().isBefore(LocalDate.now()))
                .peek((key, appointmentRequest) -> log.warn("WRONG DATE - key: [{}], appointmentRequest: [{}]", key, appointmentRequest));
        appointmentStream.to(KafkaTopicList.APPOINTMENT_WRONG_DATE, Produced.with(Serdes.String(), appointmentRequestSerde));
        return appointmentStream;
    }

}
