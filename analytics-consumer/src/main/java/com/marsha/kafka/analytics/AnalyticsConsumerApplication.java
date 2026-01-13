package com.marsha.kafka.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class AnalyticsConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnalyticsConsumerApplication.class, args);
    }
}
