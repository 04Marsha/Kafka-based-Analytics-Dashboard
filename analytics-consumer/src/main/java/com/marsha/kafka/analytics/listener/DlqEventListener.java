package com.marsha.kafka.analytics.listener;

import com.marsha.kafka.analytics.document.DlqEventDocument;
import com.marsha.kafka.analytics.repository.DlqEventRepository;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class DlqEventListener {

    private final DlqEventRepository repository;

    public DlqEventListener(DlqEventRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "user-activity-dlq", groupId = "dlq-group")
    public void consumeDlq(String message) {

        DlqEventDocument doc = new DlqEventDocument();
        doc.setRawMessage(message);
        doc.setError("Validation failed");
        doc.setFailedAt(Instant.now());
        repository.save(doc);
        System.out.println("DLQ event stored in MongoDB ");
    }
}
