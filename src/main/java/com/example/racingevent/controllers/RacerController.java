package com.example.racingevent.controllers;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.services.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/racer")
public class RacerController extends AbstractController<Racer> {
    @Autowired
    private final RacerService racerService;

    @Autowired
    public RacerController(RacerService racerService) {
        this.racerService = racerService;
    }

    @PreAuthorize("hasAnyRole(\"USER\", \"ADMIN\")")
    @GetMapping("/event/{id}")
    public ResponseEntity<List<Racer>> getRacersByEvent(@PathVariable long id) {
        List<Racer> racers = racerService.readByEvent(id);
        if (racers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(racers, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole(\"USER\", \"ADMIN\")")
    @GetMapping("/carModel/{carModel}")
    public ResponseEntity<List<Racer>> getRacersByCarModel(@PathVariable String carModel) {
        List<Racer> racers = racerService.readByCarModel(carModel);
        if (racers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(racers, headers, HttpStatus.OK);
    }

    @Override
    public RacerService getService() {
        return racerService;
    }
}


