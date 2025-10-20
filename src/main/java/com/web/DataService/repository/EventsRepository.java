package com.web.DataService.repository;

import com.web.DataService.domain.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventsRepository extends CrudRepository<Event, Long> {
    Optional<Event> findByName(String name);
}