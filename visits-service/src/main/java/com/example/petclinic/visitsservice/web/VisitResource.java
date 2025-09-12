package com.example.petclinic.visitsservice.web;

import com.example.petclinic.visitsservice.model.Visit;
import com.example.petclinic.visitsservice.model.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VisitResource {
    private final VisitRepository visitRepository;

    @PostMapping("owners/*/pets/{petId}/visits")
    public Visit createVisit(@PathVariable Integer petId, @RequestBody @Valid Visit visit) {
        visit.setPetId(petId);
        return visitRepository.save(visit);
    }

    @GetMapping("owners/*/pets/{petId}/visits")
    List<Visit> visits(@PathVariable Integer petId) {
        return visitRepository.findByPetId(petId);
    }
}
