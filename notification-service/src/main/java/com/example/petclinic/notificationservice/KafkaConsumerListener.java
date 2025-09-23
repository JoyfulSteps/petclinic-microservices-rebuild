package com.example.petclinic.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerListener {
    private final ObjectMapper objectMapper;

    public void consumeVisitCreatedEvent(String message) {
        try {
            VisitCreatedEvent event = objectMapper.readValue(message, VisitCreatedEvent.class);
            log.info("Receive notification for new vissit: Pet ID {}", event.getPetId());
        } catch (JsonProcessingException e) {
            log.error("Error deserializing message: {}", message, e);
        }
    }
}
