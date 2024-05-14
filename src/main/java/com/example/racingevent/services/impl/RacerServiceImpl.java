package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.repositories.RacerRepository;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.services.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            RacingEvent event = (RacingEvent) entity.getRcEvents();
        Long eventId = event.getId();
        event = racingEventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
        event.getRacers().add(entity);
        racingEventRepository.save(event);
        racerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Racer racer = racerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        RacingEvent event = (RacingEvent) racer.getRcEvents();
        event.getRacers().remove(racer);
        racingEventRepository.save(event);
        racerRepository.deleteById(id);
    }

    @Override
    public void edit(Racer entity) {
        Racer existingRacer = racerRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        RacingEvent event = (RacingEvent) existingRacer.getRcEvents();
        existingRacer.setName(entity.getName());
        existingRacer.setCarModel(entity.getCarModel());
        event.getRacers().add(existingRacer);
        racingEventRepository.save(event);
        racerRepository.save(existingRacer);
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


