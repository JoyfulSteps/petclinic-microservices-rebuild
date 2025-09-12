package com.example.petclinic.visitsservice.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    @Transactional(readOnly = true)
    List<Visit> findByPetId(Integer petId);
}
