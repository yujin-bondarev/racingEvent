package com.example.racingevent.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "racing_event")
@AttributeOverride(name = "id", column = @Column(name = "`event_id`"))
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class RacingEvent extends AbstractEntity{

    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_date")
    private LocalDateTime date;
    @Column(name = "location")
    private String location;
    @ManyToMany(mappedBy = "rcEvents")
  //  @JoinTable(
  //          name = "racer_event",
  //          joinColumns = @JoinColumn(name = "event_id"),
  //          inverseJoinColumns = @JoinColumn(name = "rc_id")
  //  )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Racer> racers = new HashSet<>();
    @ManyToMany(mappedBy = "vwEvents")
  //  @JoinTable(
  //          name = "viewer_event",
  //          joinColumns = @JoinColumn(name = "event_id"),
  //          inverseJoinColumns = @JoinColumn(name = "vw_id")
  //  )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Viewer> viewers = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy="spEvents", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<Sponsor> sponsors;


}
