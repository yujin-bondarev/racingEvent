package com.example.racingevent.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "racer")
@Data
@EqualsAndHashCode(callSuper = false)
public class Racer extends AbstractEntity {

    @Column(name = "rc_name")
    private String name;
    @Column(name = "car_model")
    private String carModel;
    @Column(name = "team_name")
    private String teamName;
    @ManyToMany(mappedBy = "racer")
    private Set<RacingEvent> events;

    public Set<RacingEvent> getRacingEvent() {
        return events;
    }

    public void setRacingEvent(Set<RacingEvent> events) {
        this.events = events;
    }

}
