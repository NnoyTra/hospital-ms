package com.nnoi.app.hospital_ms.config;

import com.nnoi.app.hospital_ms.entity.Appointment;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, Appointment> appointmentProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

        // Reliability settings - safe defaults for a publisher
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        configProps.put(JacksonJsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Appointment> appointmentKafkaTemplate() {
        return new KafkaTemplate<>(appointmentProducerFactory());
    }

    @Bean
    public KafkaAdmin.NewTopics topics() {
        List<NewTopic> newTopics = new ArrayList<>();

        for (Field field : KafkaTopicList.class.getDeclaredFields()) {
            boolean isConstant = Modifier.isStatic(field.getModifiers())
                    && Modifier.isFinal(field.getModifiers())
                    && field.getType() == String.class;

            if (!isConstant) {
                continue;
            }

            try {
                String topicName = (String) field.get(null);
                newTopics.add(TopicBuilder.name(topicName)
                        .partitions(3)
                        .replicas(1)
                        .build());
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Unable to read topic constant: " + field.getName(), e);
            }
        }

        return new KafkaAdmin.NewTopics(newTopics.toArray(new NewTopic[0]));
    }
}
