package com.example.racingevent.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "racer")
@AttributeOverride(name = "id", column = @Column(name = "`rc_id`"))
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Racer extends AbstractEntity {

    @Column(name = "rc_name")
    private String name;
    @Column(name = "car_model")
    private String carModel;
    @ManyToMany
    @JoinTable(
            name = "racer_event",
            joinColumns = @JoinColumn(name = "rc_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<RacingEvent> rcEvents = new HashSet<>();
}
