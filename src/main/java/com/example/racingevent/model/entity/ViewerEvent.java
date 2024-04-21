package com.example.racingevent.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "viewer_event")
public class ViewerEvent extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "vw_id")
    Viewer viewer;

    @ManyToOne
    @JoinColumn(name = "event_id")
    RacingEvent racingEvent;
}
