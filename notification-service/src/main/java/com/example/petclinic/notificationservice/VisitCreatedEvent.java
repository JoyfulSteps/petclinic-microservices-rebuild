package com.example.petclinic.notificationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitCreatedEvent {
    private Integer petId;
    private Date visitDate;
    private String description;
}
