package com.marsha.kafka.analytics.controller;

import com.marsha.kafka.analytics.document.AnalyticsEventDocument;
import com.marsha.kafka.analytics.repository.AnalyticsEventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "http://localhost:4200")
public class AnalyticsController {

    private final AnalyticsEventRepository repository;

    public AnalyticsController(AnalyticsEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/events")
    public List<AnalyticsEventDocument> getAllEvents() {
        return repository.findAll();
    }

    @GetMapping("/events/user/{userId}")
    public List<AnalyticsEventDocument> getEventsByUser(
            @PathVariable("userId") String userId
    ) {
        return repository.findByUserId(userId);
    }
}
