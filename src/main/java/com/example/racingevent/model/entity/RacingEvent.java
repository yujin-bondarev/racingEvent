package com.example.racingevent.model.entity;

import com.example.racingevent.model.serializer.RacerDTOSerializer;
import com.example.racingevent.model.serializer.SponsorDTOSerializer;
import com.example.racingevent.model.serializer.ViewerDTOSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "racing_event")
@AttributeOverride(name = "id", column = @Column(name = "`event_id`"))
@Getter @Setter @ToString
public class RacingEvent extends AbstractEntity{

    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_date")
    private LocalDateTime date;
    @Column(name = "location")
    private String location;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "rcEvents", cascade = {CascadeType.ALL})
    @JsonSerialize(using = RacerDTOSerializer.class)
    private Set<Racer> racers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "vwEvents", cascade = {CascadeType.ALL})
    @JsonSerialize(using = ViewerDTOSerializer.class)
    private Set<Viewer> viewers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy="spEvents", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonSerialize(using = SponsorDTOSerializer.class)
    private Set<Sponsor> sponsors = new HashSet<>();
}
