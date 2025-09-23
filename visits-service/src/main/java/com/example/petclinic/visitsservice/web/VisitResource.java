package com.example.petclinic.visitsservice.web;

import com.example.petclinic.visitsservice.messaging.VisitCreatedEvent;
import com.example.petclinic.visitsservice.messaging.VisitProducer;
import com.example.petclinic.visitsservice.model.Visit;
import com.example.petclinic.visitsservice.model.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VisitResource {
    private final VisitRepository visitRepository;
    private final VisitProducer visitProducer;

    @PostMapping("owners/*/pets/{petId}/visits")
    @ResponseStatus(HttpStatus.OK)
    public Visit createVisit(@PathVariable Integer petId, @RequestBody @Valid Visit visit) {
        visit.setPetId(petId);
        Visit savedVisit =  visitRepository.save(visit);

        VisitCreatedEvent event = new VisitCreatedEvent(savedVisit.getPetId(), savedVisit.getDate(), savedVisit.getDescription());
        visitProducer.sendVisitCreatedEvent(event);

        return savedVisit;
    }

    @GetMapping("owners/*/pets/{petId}/visits")
    List<Visit> visits(@PathVariable Integer petId) {
        return visitRepository.findByPetId(petId);
    }
}
