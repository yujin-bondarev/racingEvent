package com.example.racingevent.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sponsor")
@Data
@EqualsAndHashCode(callSuper = false)
public class Sponsor extends AbstractEntity{

    @Column(name = "sp_name")
    private String spName;
    @Column(name = "sp_budget")
    private String spBudget;
    @Column(name = "date_contract")
    private LocalDateTime dateOfContract;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "`event_id`", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<RacingEvent> event;
}
