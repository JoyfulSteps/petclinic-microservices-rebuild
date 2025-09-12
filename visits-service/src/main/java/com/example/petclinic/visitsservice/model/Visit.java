package com.example.petclinic.visitsservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "visits")
@Getter
@Setter
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @Column(name = "description")
    private String description;

    @Column(name = "pet_id")
    private Integer petId;
}
