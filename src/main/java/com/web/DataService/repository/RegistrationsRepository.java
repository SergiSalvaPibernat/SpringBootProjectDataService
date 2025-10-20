package com.web.DataService.repository;

import com.web.DataService.domain.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationsRepository extends CrudRepository<Registration, Long> {
    List<Registration> findByCustomerId(Long customerId);
    List<Registration> findByEventId(Long eventId);
    Optional<Registration> findByCustomerIdAndEventId(Long customerId, Long eventId);
}