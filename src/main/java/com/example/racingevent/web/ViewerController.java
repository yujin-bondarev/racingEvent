package com.example.racingevent.web;

import com.example.racingevent.model.entity.Racer;
import com.example.racingevent.model.entity.RacingEvent;
import com.example.racingevent.model.entity.Viewer;
import com.example.racingevent.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viewer")
public class ViewerController extends AbstractController<Viewer> {
    @Autowired
    private final ViewerService viewerService;

    @Autowired
    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<Viewer>> getViewersByEvent(@PathVariable long id) {
        List<Viewer> viewers = viewerService.readByEvent(id);
        if (viewers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(viewers, headers, HttpStatus.OK);
    }

    @GetMapping("/ticketType/{ticketType}")
    public ResponseEntity<List<Viewer>> getViewersByTicketType(@PathVariable String ticketType) {
        List<Viewer> viewers = viewerService.readByTicketType(ticketType);
        if (viewers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(viewers, headers, HttpStatus.OK);
    }

    @PutMapping("/{vwId}/racingEvent/{eventId}")
    public Viewer assignRacingEventToRacer(
            @PathVariable Long vwId,
            @PathVariable Long eventId
    ){
        return viewerService.assignEventToViewer(vwId, eventId);
    }

    @Override
    public ViewerService getService() {
        return viewerService;
    }
}
