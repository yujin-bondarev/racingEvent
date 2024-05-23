package com.example.racingevent.model.entity;

import com.example.racingevent.model.serializer.RacingEventSetDTOSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class Racer extends AbstractEntity {

    @Column(name = "rc_name")
    private String name;
    @Column(name = "car_model")
    private String carModel;
    @ManyToMany()
    @JoinTable(
            name = "racer_event",
            joinColumns = @JoinColumn(name = "rc_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonSerialize(using = RacingEventSetDTOSerializer.class)
    private Set<RacingEvent> rcEvents = new HashSet<>();
}
