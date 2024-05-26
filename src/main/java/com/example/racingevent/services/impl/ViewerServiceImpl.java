package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.model.repositories.ViewerRepository;
import com.example.racingevent.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ViewerServiceImpl implements ViewerService {
    @Autowired
    private final ViewerRepository viewerRepository;
    @Autowired
    private final RacingEventRepository racingEventRepository;

    @Autowired
    public ViewerServiceImpl(ViewerRepository viewerRepository, RacingEventRepository racingEventRepository) {
        this.viewerRepository = viewerRepository;
        this.racingEventRepository = racingEventRepository;
    }

    @Override
    public Viewer readById(Long id) {
        return viewerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Viewer> read() {
        return viewerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        viewerRepository.deleteById(id);
    }

    @Override
    public void edit(Viewer entity) {
        Viewer existingViewer = viewerRepository.findById(entity.getId()).orElseThrow(() -> new IllegalArgumentException("Racer not found"));
        Set<RacingEvent> events = entity.getVwEvents().stream()
                .map(event -> racingEventRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Event with id " + event.getId() + " not found")))
                .collect(Collectors.toSet());
        existingViewer.setVwName(entity.getVwName());
        existingViewer.setTicketType(entity.getTicketType());
        existingViewer.setVwEvents(events);
        viewerRepository.save(existingViewer);
    }

    @Override
    public void save(Viewer entity) {
        Set<RacingEvent> events = entity.getVwEvents().stream()
                .map(event -> racingEventRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Event with id " + event.getId() + " not found")))
                .collect(Collectors.toSet());
        entity.setVwEvents(events);
        viewerRepository.save(entity);
    }

    @Override
    public List<Viewer> readByEvent(long eventId) {
        return viewerRepository.findByVwEvents_Id(eventId);
    }

    @Override
    public List<Viewer> readByName(String vwName) {
        return viewerRepository.findByVwName(vwName);
    }

    @Override
    public List<Viewer> readByTicketType(String ticketType) {
        return viewerRepository.findByTicketType(ticketType);
    }

}
