package com.example.racingevent.model.entity;

import com.example.racingevent.model.serializer.RacingEventDTOSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sponsor")
@AttributeOverride(name = "id", column = @Column(name = "`sp_id`"))
@Getter @Setter @ToString
public class Sponsor extends AbstractEntity{

    @Column(name = "sp_name")
    private String spName;
    @Column(name = "sp_budget")
    private String budget;
    @Column(name = "date_contract")
    private LocalDateTime dateOfContract;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "`event_id`", nullable = false)
    @JsonSerialize(using = RacingEventDTOSerializer.class)
    private RacingEvent spEvents;
}
