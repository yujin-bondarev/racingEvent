package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.model.repositories.ViewerRepository;
import com.example.racingevent.services.RacingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacingEventServiceImpl implements RacingEventService {
    @Autowired
    private final RacingEventRepository racingEventRepository;
    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    public RacingEventServiceImpl(RacingEventRepository racingEventRepository) {
        this.racingEventRepository = racingEventRepository;
    }

    @Override
    public RacingEvent read(Long id) {
        return racingEventRepository.findById(id).orElse(null);
    }

    @Override
    public List<RacingEvent> read() {
        return racingEventRepository.findAll();
    }

    @Override
    public void save(RacingEvent entity) {
        racingEventRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        racingEventRepository.deleteById(id);
    }

    @Override
    public List<RacingEvent> readByName(String eventName) {
        return racingEventRepository.findByEventName(eventName);
    }

    //@Override
    public void edit(RacingEvent entity) {
        RacingEvent existingEvent = racingEventRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        existingEvent.setEventName(entity.getEventName());
        existingEvent.setDate(entity.getDate());
        existingEvent.setLocation(entity.getLocation());
        racingEventRepository.save(existingEvent);
    }

    @Override
    public List<Viewer> getEventViewersByTicketType(Long eventId, String ticketType) {
        return viewerRepository.findByVwEvents_IdAndTicketType(eventId, ticketType);
    }
}

