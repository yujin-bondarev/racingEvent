package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.repositories.RacerRepository;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.services.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RacerServiceImpl implements RacerService {
    @Autowired
    private final RacerRepository racerRepository;
    @Autowired
    private final RacingEventRepository racingEventRepository;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, RacingEventRepository racingEventRepository) {
        this.racerRepository = racerRepository;
        this.racingEventRepository = racingEventRepository;
    }

    @Override
    public Racer read(Long id) {
        return racerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Racer> read() {
        return racerRepository.findAll();
    }

    @Override
    public void save(Racer entity) {
        Set<RacingEvent> events = entity.getRcEvents().stream()
                .map(event -> racingEventRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Event with id " + event.getId() + " not found")))
                .collect(Collectors.toSet());
        entity.setRcEvents(events);
        racerRepository.save(entity);
    }

    @Override
    public void edit(Racer entity) {
        Racer existingRacer = racerRepository.findById(entity.getId()).orElseThrow(() -> new IllegalArgumentException("Racer not found"));
        Set<RacingEvent> events = entity.getRcEvents().stream()
                .map(event -> racingEventRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Event with id " + event.getId() + " not found")))
                .collect(Collectors.toSet());
        existingRacer.setName(entity.getName());
        existingRacer.setCarModel(entity.getCarModel());
        existingRacer.setRcEvents(events);
        racerRepository.save(existingRacer);
    }

    @Override
    public void delete(Long id) {
        racerRepository.deleteById(id);
    }

    @Override
    public List<Racer> readByEvent(long eventId) {
        return racerRepository.findByRcEvents_Id(eventId);
    }

    @Override
    public List<Racer> readByName(String name) {
        return racerRepository.findByName(name);
    }

    @Override
    public List<Racer> readByCarModel(String carModel) {
        return racerRepository.findByCarModel(carModel);
    }
}


