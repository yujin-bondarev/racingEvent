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
    private final RacerRepository racerRepository;
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
        RacingEvent event = (RacingEvent) entity.getRacingEvent();
        Long eventId = event.getId();
        event = racingEventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
        // Добавляем гонщика в список гонщиков мероприятия
        event.getRacers().add(entity);
        racingEventRepository.save(event);
        // Сохраняем гонщика
        racerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        // Находим гонщика по ID
        Racer racer = racerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        // Получаем мероприятие, к которому принадлежит гонщик
        RacingEvent event = (RacingEvent) racer.getRacingEvent();
        // Удаляем гонщика из списка гонщиков мероприятия
        event.getRacers().remove(racer);
        racingEventRepository.save(event);
        // Удаляем гонщика
        racerRepository.deleteById(id);
    }

    @Override
    public void edit(Racer entity) {
        // Находим гонщика по ID
        Racer existingRacer = racerRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        // Получаем мероприятие, к которому принадлежит гонщик
        RacingEvent event = (RacingEvent) existingRacer.getRacingEvent();
        // Обновляем свойства гонщика
        existingRacer.setName(entity.getName());
        existingRacer.setCarModel(entity.getCarModel());
        existingRacer.setTeamName(entity.getTeamName());
        // Обновляем список гонщиков мероприятия (если необходимо)
        event.getRacers().add(existingRacer);
        racingEventRepository.save(event);
        // Сохраняем обновленного гонщика
        racerRepository.save(existingRacer);
    }

    @Override
    public List<Racer> readByEvent(long eventId) {
        return racerRepository.findByRacerEvents_RacingEvent(eventId);
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


