package com.example.racingevent.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class RacingEvent {
    @Id
    private Long id;
    private String Name;
    private String DateOfEvent;
    private String Location;
    private Long event_id;
}
