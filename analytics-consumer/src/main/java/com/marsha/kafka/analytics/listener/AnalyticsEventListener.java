package com.marsha.kafka.analytics.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsha.kafka.analytics.document.AnalyticsEventDocument;
import com.marsha.kafka.analytics.repository.AnalyticsEventRepository;
import com.marsha.kafka.common.model.UserActivityEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsEventListener {

    private final AnalyticsEventRepository repository;
    private final KafkaTemplate<String, String> dlqKafkaTemplate;
    private final ObjectMapper objectMapper;

    public AnalyticsEventListener(
            AnalyticsEventRepository repository,
            KafkaTemplate<String, String> dlqKafkaTemplate,
            ObjectMapper objectMapper) {
        this.repository = repository;
        this.dlqKafkaTemplate = dlqKafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "user-activity",
            groupId = "analytics-group"
    )
    public void consume(String message) throws Exception {

        try {
            UserActivityEvent event = objectMapper.readValue(message, UserActivityEvent.class);

            if(event.getEventType() == null || !event.getEventType().matches("[A-Z_]+")) {
                throw new IllegalArgumentException(
                        "Invalid event type: " + event.getEventType()
                );
            }

            if(event.getUserId() == null || event.getUserId().isBlank()) {
                throw new IllegalArgumentException("User id is missing");
            }

            AnalyticsEventDocument doc = new AnalyticsEventDocument();
            doc.setEventId(event.getEventId());
            doc.setUserId(event.getUserId());
            doc.setEventType(event.getEventType());
            doc.setSource(event.getSource());
            doc.setMetadata(event.getMetadata());
            doc.setTimestamp(event.getTimestamp());

            System.out.println("Analytics received event: ");
            System.out.println("User: " + event.getUserId());
            System.out.println("Type: " + event.getEventType());
            System.out.println("Time: " + event.getTimestamp());

            repository.save(doc);

            System.out.println("Stored event in Mongodb");
        } catch (Exception e) {
            System.out.println("Failed to process event, sending to DLQ");
            dlqKafkaTemplate.send("user-activity-dlq", message);
            e.printStackTrace();
        }
    }
}
