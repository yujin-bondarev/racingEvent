package com.example.racingevent.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Sponsor {
    @Id
    private Long id;
    private String name;
    private float budget;
    private String dateOfContract;
    private Long event_id;
}
