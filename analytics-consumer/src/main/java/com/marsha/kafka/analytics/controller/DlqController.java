package com.marsha.kafka.analytics.controller;

import com.marsha.kafka.analytics.document.DlqEventDocument;
import com.marsha.kafka.analytics.repository.DlqEventRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DlqController {
    private final DlqEventRepository repository;

    public DlqController(DlqEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/dlq/events")
    public List<DlqEventDocument> getDlqEvents() {
        return repository.findAll();
    }
}
