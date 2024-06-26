package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Long> {
    List<Racer> findByRcEvents_Id(long id);
    List<Racer> findByName(String name);
    List<Racer> findByCarModel(String carModel);
}
