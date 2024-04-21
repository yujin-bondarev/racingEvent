package com.example.racingevent.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "racer")
public class Racer extends AbstractEntity {

    @Column(name = "rc_name")
    private String name;
    @Column(name = "car_model")
    private String carModel;
    @Column(name = "team_name")
    private String teamName;

    @OneToMany(mappedBy = "racer")
    private Set<RacerEvent> racerEvents;


}
