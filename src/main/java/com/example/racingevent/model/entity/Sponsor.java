package com.example.racingevent.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sponsor")
@AttributeOverride(name = "id", column = @Column(name = "`sp_id`"))
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Sponsor extends AbstractEntity{

    @Column(name = "sp_name")
    private String spName;
    @Column(name = "sp_budget")
    private String budget;
    @Column(name = "date_contract")
    private LocalDateTime dateOfContract;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "`event_id`", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private RacingEvent spEvents;
}
