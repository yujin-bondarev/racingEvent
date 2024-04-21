package com.example.racingevent.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "racing_event")
public class RacingEvent extends AbstractEntity{

    @Column(name = "event_name")
    private String event_name;
    @Column(name = "event_date")
    private LocalDateTime event_date;
    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy="racing_event")
    private Set<Sponsor> sponsors;

    @OneToMany(mappedBy="racing_event")
    private Set<RacerEvent> racers;

    @OneToMany(mappedBy="racing_event")
    private Set<ViewerEvent> viewers;


}
