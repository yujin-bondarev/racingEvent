package com.example.racingevent.web;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.services.SponsorService;
import com.example.racingevent.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sponsor")
public class SponsorController extends AbstractController<Sponsor> {

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

    @Override
    public SponsorService getService() {
        return sponsorService;
    }
}
