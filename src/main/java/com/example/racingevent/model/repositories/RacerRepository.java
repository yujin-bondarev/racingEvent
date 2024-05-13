package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Long> {
    List<Racer> findByRacerEvents_RacingEvent(RacingEvent racingEvent);
    List<Racer> findByName(String name);
    List<Racer> findByCarModel(String carModel);
}
