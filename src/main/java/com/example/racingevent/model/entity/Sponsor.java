package com.example.racingevent.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sponsor")
public class Sponsor extends AbstractEntity{

    @Column(name = "sp_name")
    private String spName;
    @Column(name = "sp_budget")
    private String spBudget;
    @Column(name = "date_contract")
    private LocalDateTime dateOfContract;

    @ManyToOne(fetch = FetchType.LAZY)
    private RacingEvent event;
}
