package com.example.racingevent.services;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;

import java.util.List;

public interface ViewerService extends Service<Viewer> {
    List<Viewer> readByEvent(long eventId);
    List<Viewer> readByName(String vwName);

    List<Viewer> readByTicketType(String ticketType);
}

