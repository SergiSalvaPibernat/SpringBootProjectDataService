package com.web.DataService.service;

import java.util.Optional;
import com.web.DataService.domain.Event;
import com.web.DataService.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventsService {

    @Autowired
    private EventsRepository repo;

    @Override
    public Iterable<Event> findAllEvents() {
        return repo.findAll();
    }

    @Override
    public Optional<Event> findEventById(long id) {
        return repo.findById(id);
    }

    @Override
    public Event updateEvent(long id, Event event) {
        Event existingEvent = repo.findById(id).orElseThrow(() -> new RuntimeException("Event not found with id"));
        existingEvent.setName(event.getName());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setMaxCapacity(event.getMaxCapacity());
        existingEvent.setCurrentRegistrations(event.getCurrentRegistrations());
        return repo.save(existingEvent);
    }

    @Override
    public void saveEvent(Event event) {
        Event existingEvent = repo.findByName(event.getName()).orElse(null);
        if(existingEvent == null) {
            repo.save(event);
        } else {
            throw new RuntimeException("Event with this name already exists");
        }
    }

    @Override
    public void deleteEvent(long id) {
        Event event = repo.findById(id).orElseThrow(() -> new RuntimeException("Event not found with id"));
        repo.deleteById(id);
    }
}