package com.example.racingevent.model.entity;

import com.example.racingevent.model.serializer.RacingEventSetDTOSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "viewer")
@AttributeOverride(name = "id", column = @Column(name = "`vw_id`"))
@Getter
@Setter
@ToString
public class Viewer extends AbstractEntity {
    @Column(name = "vw_name")
    private String vwName;
    @Column(name = "ticket_type")
    private String ticketType;
    @ManyToMany()
    @JoinTable(
            name = "viewer_event",
            joinColumns = @JoinColumn(name = "vw_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonSerialize(using = RacingEventSetDTOSerializer.class)
    private Set<RacingEvent> vwEvents = new HashSet<>();

}
