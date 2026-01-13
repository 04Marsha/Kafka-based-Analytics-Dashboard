package com.marsha.kafka.analytics.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "dlq_events")
public class DlqEventDocument {
    @Id
    private String id;

    private String rawMessage;
    private String error;
    private Instant failedAt;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getRawMessage() {
        return rawMessage;
    }
    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    public Instant getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(Instant failedAt) {
        this.failedAt = failedAt;
    }
}
