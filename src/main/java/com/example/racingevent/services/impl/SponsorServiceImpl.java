package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.model.repositories.SponsorRepository;
import com.example.racingevent.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService {
    @Autowired
    private final SponsorRepository sponsorRepository;
    @Autowired
    private final RacingEventRepository racingEventRepository;

    @Autowired
    public SponsorServiceImpl(SponsorRepository sponsorRepository, RacingEventRepository racingEventRepository) {
        this.sponsorRepository = sponsorRepository;
        this.racingEventRepository = racingEventRepository;
    }

    @Override
    public Sponsor readById(Long id) {
        return sponsorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Sponsor> read() {
        return sponsorRepository.findAll();
    }

    @Override
    public void save(Sponsor entity) {
        RacingEvent event = racingEventRepository.findById(entity.getSpEvents().getId()).orElseThrow(() -> new IllegalArgumentException("Event with id " + entity.getSpEvents().getId() + " not found"));
        entity.setSpEvents(event);
        sponsorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        sponsorRepository.deleteById(id);
    }

    @Override
    public void edit(Sponsor entity) {
        Sponsor existingSponsor = sponsorRepository.findById(entity.getId()).orElseThrow(() -> new IllegalArgumentException("Sponsor not found"));
        RacingEvent existingEvent = racingEventRepository.findById(entity.getSpEvents().getId()).orElseThrow(() -> new IllegalArgumentException("Event not found"));
        existingSponsor.setSpName(entity.getSpName());
        existingSponsor.setBudget(entity.getBudget());
        existingSponsor.setDateOfContract(entity.getDateOfContract());
        existingSponsor.setSpEvents(existingEvent);
        sponsorRepository.save(existingSponsor);
    }

    @Override
    public List<Sponsor> readByEvent(long eventId) {
        return sponsorRepository.findBySpEvents_Id(eventId);
    }

    @Override
    public List<Sponsor> readByName(String name) {
        return sponsorRepository.findBySpName(name);
    }

}
