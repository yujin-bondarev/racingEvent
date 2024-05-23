package com.example.racingevent.controllers;

import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Sponsor;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.services.RacingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/viewersByTicket/{ticketType}")
    public ResponseEntity<List<Viewer>> getEventViewersByTicketType(@PathVariable Long id, @PathVariable String ticketType) {
        List<Viewer> viewers = racingEventService.getEventViewersByTicketType(id, ticketType);
        return new ResponseEntity<>(viewers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/sponsor-budgets-sum")
    public ResponseEntity<BigDecimal> getSponsorBudgetsSum(@PathVariable Long id) {
        BigDecimal sum = racingEventService.getSumOfSponsorBudgets(id);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/sponsorContract/{month}")
    public ResponseEntity<List<Sponsor>> getSponsorsContractedBeforeEvent(@PathVariable Long id,
                                                                          @PathVariable int month) {
        List<Sponsor> sponsors = racingEventService.getSponsorsContractedBeforeEvent(id, month);
        return new ResponseEntity<>(sponsors, HttpStatus.OK);
    }

    @Override
    public RacingEventService getService() {
        return racingEventService;
    }
}
