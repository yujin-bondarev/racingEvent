package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.View;
import java.util.List;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, Long> {
    List<Viewer> findByVwName(String vwName);
    List<Viewer> findByViewerEvents_RacingEvent(RacingEvent racingEvent);

    List<Viewer> findByTicketType(String ticketType);
}