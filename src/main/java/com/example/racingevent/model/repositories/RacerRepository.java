package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacerRepository extends JpaRepository<Racer, Long> {
}
