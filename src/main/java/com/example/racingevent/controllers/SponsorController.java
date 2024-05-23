package com.example.racingevent.controllers;

import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createSponsor(@RequestBody Sponsor sponsor) {
        try {
            sponsorService.save(sponsor);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/edit")
    public ResponseEntity<?> updateSponsor(@RequestBody Sponsor sponsor) {
        try {
            sponsorService.edit(sponsor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public SponsorService getService() {
        return sponsorService;
    }
}
