package com.web.DataService.api;

import java.util.Optional;
import com.web.DataService.domain.Event;
import com.web.DataService.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EventAPI {

    @Autowired
    EventsService service;

    @GetMapping("/events")
    public ResponseEntity<Iterable<Event>> getAllEvents() {
        Iterable<Event> events = service.findAllEvents();
        if (events == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id) {
        Event event = service.findEventById(id).orElse(null);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> putEvent(@PathVariable long id, @RequestBody Event event) {
        try {
            Event updatedEvent = service.updateEvent(id, event);
            return ResponseEntity.ok(updatedEvent);
        } catch (RuntimeException r) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events")
    public ResponseEntity<Event> postEvent(@RequestBody Event event) {
        try {
            System.out.println("Saved " + event.toString());
            service.saveEvent(event);
            System.out.println("Saved and updated " + event.toString());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Enter in the catch: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") long id) {
        try {
            service.deleteEvent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}