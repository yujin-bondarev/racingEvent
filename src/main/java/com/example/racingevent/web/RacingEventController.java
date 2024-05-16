package com.example.racingevent.web;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.services.RacingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/racingEvent")
public class RacingEventController extends AbstractController<RacingEvent> {
    @Autowired
    private final RacingEventService racingEventService;

    @Autowired
    public RacingEventController(RacingEventService racingEventService) {
        this.racingEventService = racingEventService;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<RacingEvent>> getEventByName(@PathVariable String name) {
        List<RacingEvent> events = racingEventService.readByName(name);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, headers, HttpStatus.OK);
    }

    @Override
    public RacingEventService getService() {
        return racingEventService;
    }
}
