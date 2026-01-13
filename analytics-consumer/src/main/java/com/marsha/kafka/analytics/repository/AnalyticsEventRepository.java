package com.marsha.kafka.analytics.repository;

import com.marsha.kafka.analytics.document.AnalyticsEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnalyticsEventRepository extends MongoRepository<AnalyticsEventDocument, String> {
    List<AnalyticsEventDocument> findByUserId(String userId);
}
