package com.marsha.kafka.common.model;

import java.time.Instant;
import java.util.Map;

public class UserActivityEvent {
    private String eventId;
    private String userId;
    private String eventType;
    private String source;
    private Map<String, Object> metadata;
    private Instant timestamp;

    public UserActivityEvent() {}

    public UserActivityEvent(String eventId, String userId, String eventType, String source, Map<String, Object> metadata, Instant timestamp) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventType = eventType;
        this.source = source;
        this.metadata = metadata;
        this.timestamp = timestamp;
    }

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
