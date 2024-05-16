package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.model.repositories.ViewerRepository;
import com.example.racingevent.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public Viewer read(Long id) {
        return viewerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Viewer> read() {
        return viewerRepository.findAll();
    }

    @Override
    public void save(Viewer entity) {
        viewerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        viewerRepository.deleteById(id);
    }

    @Override
    public void edit(Viewer entity) {
        Viewer existingViewer = viewerRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        existingViewer.setVwName(entity.getVwName());
        existingViewer.setTicketType(entity.getTicketType());
        RacingEvent event = (RacingEvent) existingViewer.getVwEvents();
        event.getViewers().add(existingViewer);
        racingEventRepository.save(event);
        viewerRepository.save(existingViewer);
    }

    @Override
    public Viewer assignEventToViewer(Long vwId, Long eventId) {
        Set<RacingEvent> eventSet = null;
        Viewer viewer = viewerRepository.findById(vwId).orElseThrow(IllegalArgumentException::new);
        RacingEvent racingEvent = racingEventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
        eventSet =  viewer.getVwEvents();
        eventSet.add(racingEvent);
        viewer.setVwEvents(eventSet);
        return viewerRepository.save(viewer);
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
