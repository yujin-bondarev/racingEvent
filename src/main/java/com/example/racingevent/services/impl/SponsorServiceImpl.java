package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.model.repositories.SponsorRepository;
import com.example.racingevent.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public Sponsor read(Long id) {
        return sponsorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Sponsor> read() {
        return sponsorRepository.findAll();
    }

    @Override
    public void save(Sponsor entity) {
        sponsorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        sponsorRepository.deleteById(id);
    }

    @Override
    public void edit(Sponsor entity) {
        Sponsor existingSponsor = sponsorRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        existingSponsor.setSpName(entity.getSpName());
        existingSponsor.setBudget(entity.getBudget());
        existingSponsor.setDateOfContract(entity.getDateOfContract());
        RacingEvent event = existingSponsor.getSpEvents();
        event.getSponsors().add(existingSponsor);
        racingEventRepository.save(event);
        sponsorRepository.save(existingSponsor);
    }

    public Sponsor assignEventToSponsor(Sponsor sponsor, Long eventId) {
        RacingEvent racingEvent = racingEventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
        sponsor.setSpEvents(racingEvent);
        return sponsorRepository.save(sponsor);
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
