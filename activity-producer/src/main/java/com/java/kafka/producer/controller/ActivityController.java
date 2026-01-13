package com.java.kafka.producer.controller;

import com.java.kafka.producer.service.ActivityProducerService;
import com.marsha.kafka.common.model.UserActivityEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {
    private final ActivityProducerService producerService;

    public ActivityController(ActivityProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<Void> track(@RequestBody UserActivityEvent event) {
        producerService.publish(event);
        return ResponseEntity.accepted().build();
    }
}