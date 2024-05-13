package com.example.racingevent.services.impl;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.model.repositories.RacingEventRepository;
import com.example.racingevent.model.repositories.ViewerRepository;
import com.example.racingevent.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewerServiceImpl implements ViewerService {
    private final ViewerRepository viewerRepository;
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
        // Получаем мероприятие (RacingEvent), к которому принадлежит зритель
        RacingEvent event = (RacingEvent) entity.getEvent();
        Long eventId = event.getId();
        event = racingEventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
        // Добавляем зрителя в список зрителей мероприятия
        event.getViewers().add(entity);
        racingEventRepository.save(event);
        // Сохраняем зрителя
        viewerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        // Находим зрителя по ID
        Viewer viewer = viewerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        // Получаем мероприятие, к которому принадлежит зритель
        RacingEvent event = (RacingEvent) viewer.getEvent();
        // Удаляем зрителя из списка зрителей мероприятия
        event.getViewers().remove(viewer);
        racingEventRepository.save(event);
        // Удаляем зрителя
        viewerRepository.deleteById(id);
    }

    @Override
    public void edit(Viewer entity) {
        // Находим зрителя по ID
        Viewer existingViewer = viewerRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        // Обновляем свойства зрителя
        existingViewer.setVwName(entity.getVwName());
        existingViewer.setTicketType(entity.getTicketType());
        // Обновляем список зрителей мероприятия (если необходимо)
        RacingEvent event = (RacingEvent) existingViewer.getEvent();
        event.getViewers().add(existingViewer);
        racingEventRepository.save(event);
        // Сохраняем обновленного зрителя
        viewerRepository.save(existingViewer);
    }

    @Override
    public List<Viewer> readByEvent(long eventId) {
        return viewerRepository.findByViewerEvents_RacingEvent(eventId);
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
