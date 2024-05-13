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
    private final SponsorRepository sponsorRepository;
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
        // Получаем мероприятие (RacingEvent), к которому принадлежит спонсор
        RacingEvent event = (RacingEvent) entity.getEvent();
        Long eventId = event.getId();
        event = racingEventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
        // Добавляем спонсора в список спонсоров мероприятия
        event.getSponsors().add(entity);
        racingEventRepository.save(event);
        // Сохраняем спонсора
        sponsorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        // Находим спонсора по ID
        Sponsor sponsor = sponsorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        // Получаем мероприятие, к которому принадлежит спонсор
        RacingEvent event = (RacingEvent) sponsor.getEvent();
        // Удаляем спонсора из списка спонсоров мероприятия
        event.getSponsors().remove(sponsor);
        racingEventRepository.save(event);
        // Удаляем спонсора
        sponsorRepository.deleteById(id);
    }

    @Override
    public void edit(Sponsor entity) {
        // Находим спонсора по ID
        Sponsor existingSponsor = sponsorRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        // Обновляем свойства спонсора
        existingSponsor.setSpName(entity.getSpName());
        existingSponsor.setSpBudget(entity.getSpBudget());
        existingSponsor.setDateOfContract(entity.getDateOfContract());
        // Обновляем список спонсоров мероприятия (если необходимо)
        RacingEvent event = (RacingEvent) existingSponsor.getEvent();
        event.getSponsors().add(existingSponsor);
        racingEventRepository.save(event);
        // Сохраняем обновленного спонсора
        sponsorRepository.save(existingSponsor);
    }

    @Override
    public List<Sponsor> readByEvent(long eventId) {
        return sponsorRepository.findBySponsorEvents_RacingEvent(eventId);
    }

    @Override
    public List<Sponsor> readByName(String name) {
        return sponsorRepository.findBySpName(name);
    }

}
