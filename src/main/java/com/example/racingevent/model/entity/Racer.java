package com.example.racingevent.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Racer {
    @Id
    private Long id;
    private String name;
    private String carModel;
    private String teamName;
    private Long event_id;
}
