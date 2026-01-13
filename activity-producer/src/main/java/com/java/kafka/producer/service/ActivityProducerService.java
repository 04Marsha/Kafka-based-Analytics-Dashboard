package com.java.kafka.producer.service;

import com.marsha.kafka.common.model.UserActivityEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ActivityProducerService {

    private final KafkaTemplate<String, UserActivityEvent> kafkaTemplate;

    public ActivityProducerService(KafkaTemplate<String, UserActivityEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(UserActivityEvent event) {
        event.setEventId(UUID.randomUUID().toString());
        event.setTimestamp(Instant.now());
        kafkaTemplate.send("user-activity", event.getUserId(), event);
    }

}