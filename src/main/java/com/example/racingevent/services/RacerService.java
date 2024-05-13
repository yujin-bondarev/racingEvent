package com.example.racingevent.services;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;

import java.util.List;

public interface RacerService extends Service<Racer> {
        List<Racer> readByEvent(long eventId);
        List<Racer> readByName(String name);
        List<Racer> readByCarModel(String carModel);
}

