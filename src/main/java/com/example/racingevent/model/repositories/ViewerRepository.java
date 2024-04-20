package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewerRepository extends JpaRepository<Viewer, Long> {
}
