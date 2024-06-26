package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.model.repositories.ViewerRepository;
import com.example.racingevent.services.RacingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public RacingEvent readById(Long id) {
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
    public BigDecimal getSumOfSponsorBudgets(Long eventId) {
        RacingEvent event = racingEventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));
        return event.getSponsors().stream()
                .map(sponsor -> new BigDecimal(sponsor.getBudget()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Sponsor> getSponsorsContractedBeforeEvent(Long eventId, int months) {
        RacingEvent event = racingEventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));
        LocalDateTime twoMonthsBeforeEvent = event.getDate().minusMonths(months);
        return event.getSponsors().stream()
                .filter(sponsor -> sponsor.getDateOfContract().isAfter(twoMonthsBeforeEvent) && sponsor.getDateOfContract().isBefore(event.getDate()))
                .collect(Collectors.toList());
    }


    @Override
    public List<Viewer> getEventViewersByTicketType(Long eventId, String ticketType) {
        RacingEvent event = racingEventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));
        return viewerRepository.findByVwEvents_IdAndTicketType(event, ticketType);
    }
}

