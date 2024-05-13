package com.example.racingevent.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "racing_event")
@Data
@EqualsAndHashCode(callSuper = false)
public class RacingEvent extends AbstractEntity{

    @Column(name = "event_name")
    private String name;
    @Column(name = "event_date")
    private LocalDateTime date;
    @Column(name = "location")
    private String location;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="racing_event", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<Sponsor> sponsors;

    @ManyToMany
    @JoinTable(
            name = "racer_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "rc_id")
    )
    private Set<Racer> racers;

    @ManyToMany
    @JoinTable(
            name = "viewer_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "vw_id")
    )
    private Set<Viewer> viewers;


}
