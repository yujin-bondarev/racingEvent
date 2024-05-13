package com.example.racingevent.services;

import com.example.racingevent.model.entity.RacingEvent;

import java.util.List;

public interface RacingEventService extends Service<RacingEvent> {
    List<RacingEvent> readByName(String eventName);
}
