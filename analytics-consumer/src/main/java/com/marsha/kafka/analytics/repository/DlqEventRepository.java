package com.marsha.kafka.analytics.repository;

import com.marsha.kafka.analytics.document.DlqEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DlqEventRepository extends MongoRepository<DlqEventDocument, String> {
}
