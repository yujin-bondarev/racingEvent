package com.example.racingevent.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "racer_event")
public class RacerEvent extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "rc_id")
    Racer racer;

    @ManyToOne
    @JoinColumn(name = "event_id")
    RacingEvent racingEvent;
}
