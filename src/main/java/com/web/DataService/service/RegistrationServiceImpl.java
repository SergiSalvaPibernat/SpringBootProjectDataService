package com.web.DataService.service;

import java.util.List;
import java.util.Optional;
import com.web.DataService.domain.Registration;
import com.web.DataService.repository.RegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationsService {

    @Autowired
    private RegistrationsRepository repo;

    @Override
    public Iterable<Registration> findAllRegistrations() {
        return repo.findAll();
    }

    @Override
    public Optional<Registration> findRegistrationById(long id) {
        return repo.findById(id);
    }

    @Override
    public List<Registration> findRegistrationsByCustomerId(long customerId) {
        return repo.findByCustomerId(customerId);
    }

    @Override
    public List<Registration> findRegistrationsByEventId(long eventId) {
        return repo.findByEventId(eventId);
    }

    @Override
    public Registration updateRegistration(long id, Registration registration) {
        Registration existingRegistration = repo.findById(id).orElseThrow(() -> new RuntimeException("Registration not found with id"));
        existingRegistration.setCustomerId(registration.getCustomerId());
        existingRegistration.setEventId(registration.getEventId());
        existingRegistration.setRegistrationDate(registration.getRegistrationDate());
        existingRegistration.setStatus(registration.getStatus());
        return repo.save(existingRegistration);
    }

    @Override
    public void saveRegistration(Registration registration) {
        // Check if customer is already registered for this event
        Optional<Registration> existingRegistration = repo.findByCustomerIdAndEventId(
            registration.getCustomerId(), registration.getEventId());
        
        if(existingRegistration.isEmpty()) {
            repo.save(registration);
        } else {
            throw new RuntimeException("Customer is already registered for this event");
        }
    }

    @Override
    public void deleteRegistration(long id) {
        Registration registration = repo.findById(id).orElseThrow(() -> new RuntimeException("Registration not found with id"));
        repo.deleteById(id);
    }

    @Override
    public boolean isCustomerRegisteredForEvent(long customerId, long eventId) {
        Optional<Registration> registration = repo.findByCustomerIdAndEventId(customerId, eventId);
        return registration.isPresent();
    }
}