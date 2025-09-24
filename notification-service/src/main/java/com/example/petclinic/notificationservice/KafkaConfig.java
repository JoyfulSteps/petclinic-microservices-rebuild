package com.example.petclinic.notificationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Configuration
public class KafkaConfig {

}


@Component
@RequiredArgsConstructor
class VisitEventListener {
    private final KafkaConsumerListener kafkaConsumerListener;

    @KafkaListener(topics = "visits.created", groupId = "notification-group")
    public void handleVisitCreatedEvent(String message) {
        kafkaConsumerListener.consumeVisitCreatedEvent(message);
    }
}