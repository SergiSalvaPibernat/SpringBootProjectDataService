package com.web.DataService.service;

import java.util.List;
import java.util.Optional;
import com.web.DataService.domain.Registration;

public interface RegistrationsService {
    public Iterable<Registration> findAllRegistrations();
    public Optional<Registration> findRegistrationById(long id);
    public List<Registration> findRegistrationsByCustomerId(long customerId);
    public List<Registration> findRegistrationsByEventId(long eventId);
    public Registration updateRegistration(long id, Registration registration);
    public void saveRegistration(Registration registration);
    public void deleteRegistration(long id);
    public boolean isCustomerRegisteredForEvent(long customerId, long eventId);
}