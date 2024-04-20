package com.example.racingevent.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Viewer {
    @Id
    private Long id;
    private String name;
    private String ticketType;
    private Long event_id;
}
