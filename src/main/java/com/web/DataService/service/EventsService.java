package com.web.DataService.service;

import java.util.Optional;
import com.web.DataService.domain.Event;

public interface EventsService {
    public Iterable<Event> findAllEvents();
    public Optional<Event> findEventById(long id);
    public Event updateEvent(long id, Event event);
    public void saveEvent(Event event);
    public void deleteEvent(long id);
}