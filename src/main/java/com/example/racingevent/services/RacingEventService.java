package com.example.racingevent.services;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;

import java.math.BigDecimal;
import java.util.List;

public interface RacingEventService extends Service<RacingEvent> {
    List<RacingEvent> readByName(String eventName);
    List<Viewer> getEventViewersByTicketType(Long eventId, String ticketType);

    BigDecimal getSumOfSponsorBudgets(Long id);
}
