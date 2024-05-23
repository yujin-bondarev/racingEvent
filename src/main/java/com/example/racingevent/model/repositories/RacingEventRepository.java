package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.RacingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacingEventRepository extends JpaRepository<RacingEvent, Long> {
    List<RacingEvent> findByEventName(String eventName);
}