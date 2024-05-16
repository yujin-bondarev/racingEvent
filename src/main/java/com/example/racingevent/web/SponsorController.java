package com.example.racingevent.web;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController<Sponsor> {
    @Autowired
    private final SponsorService sponsorService;

    @Autowired
    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<Sponsor>> getSponsorsByEvent(@PathVariable long id) {
        List<Sponsor> sponsors = sponsorService.readByEvent(id);
        if (sponsors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sponsors, headers, HttpStatus.OK);
    }

    @PostMapping("/create/assignEvent/{eventId}")
    public Sponsor assignEventToSponsor(
            @RequestBody Sponsor sponsor,
            @PathVariable Long eventId) {
        return sponsorService.assignEventToSponsor(sponsor, eventId);
    }
    @Override
    public SponsorService getService() {
        return sponsorService;
    }
}
