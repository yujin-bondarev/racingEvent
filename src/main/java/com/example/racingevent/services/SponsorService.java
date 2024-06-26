package com.example.racingevent.services;

import com.example.racingevent.model.entity.Sponsor;

import java.util.List;

public interface SponsorService extends Service<Sponsor> {
    List<Sponsor> readByEvent(long eventId);
    List<Sponsor> readByName(String spName);
}

