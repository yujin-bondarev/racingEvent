package com.example.racingevent.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "viewer")
public class Viewer extends AbstractEntity {
    @Column(name = "vw_name")
    private String vwName;
    @Column(name = "ticket_type")
    private String ticketType;

    @OneToMany(mappedBy = "viewer")
    private Set<ViewerEvent> viewerEvents;

}
