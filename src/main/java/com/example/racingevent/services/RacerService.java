package com.example.racingevent.services;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;

import java.util.List;

public interface RacerService extends Service<Racer> {
        List<Racer> readByEvent(long eventId);
        List<Racer> readByName(String name);
        List<Racer> readByCarModel(String carModel);

        Racer assignEventToRacer(Long rcId, Long eventId);
}

