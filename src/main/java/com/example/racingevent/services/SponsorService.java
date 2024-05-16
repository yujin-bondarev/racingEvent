package com.example.racingevent.services;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.model.entity.Viewer;

import java.util.List;

public interface SponsorService extends Service<Sponsor> {
    List<Sponsor> readByEvent(long eventId);
    List<Sponsor> readByName(String spName);
    Sponsor assignEventToSponsor(Sponsor sponsor, Long eventId);
}

