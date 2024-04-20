package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.RacingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacingEventRepository extends JpaRepository<RacingEvent, Long> {
}
