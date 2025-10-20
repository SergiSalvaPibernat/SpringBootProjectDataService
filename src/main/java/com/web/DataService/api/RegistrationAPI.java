package com.web.DataService.api;

import java.util.List;
import com.web.DataService.domain.Registration;
import com.web.DataService.service.RegistrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RegistrationAPI {

    @Autowired
    RegistrationsService service;

    @GetMapping("/registrations")
    public ResponseEntity<Iterable<Registration>> getAllRegistrations() {
        Iterable<Registration> registrations = service.findAllRegistrations();
        if (registrations == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registrations/{id}")
    public ResponseEntity<Registration> getRegistration(@PathVariable long id) {
        Registration registration = service.findRegistrationById(id).orElse(null);
        if (registration == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registration);
    }

    @GetMapping("/registrations/customer/{customerId}")
    public ResponseEntity<List<Registration>> getRegistrationsByCustomer(@PathVariable long customerId) {
        List<Registration> registrations = service.findRegistrationsByCustomerId(customerId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registrations/event/{eventId}")
    public ResponseEntity<List<Registration>> getRegistrationsByEvent(@PathVariable long eventId) {
        List<Registration> registrations = service.findRegistrationsByEventId(eventId);
        return ResponseEntity.ok(registrations);
    }

    @PutMapping("/registrations/{id}")
    public ResponseEntity<Registration> putRegistration(@PathVariable long id, @RequestBody Registration registration) {
        try {
            Registration updatedRegistration = service.updateRegistration(id, registration);
            return ResponseEntity.ok(updatedRegistration);
        } catch (RuntimeException r) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registrations")
    public ResponseEntity<Registration> postRegistration(@RequestBody Registration registration) {
        try {
            System.out.println("Saved " + registration.toString());
            service.saveRegistration(registration);
            System.out.println("Saved and updated " + registration.toString());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Enter in the catch: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/registrations/{id}")
    public ResponseEntity<Registration> deleteRegistration(@PathVariable("id") long id) {
        try {
            service.deleteRegistration(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/registrations/check/{customerId}/{eventId}")
    public ResponseEntity<Boolean> checkRegistration(@PathVariable long customerId, @PathVariable long eventId) {
        boolean isRegistered = service.isCustomerRegisteredForEvent(customerId, eventId);
        return ResponseEntity.ok(isRegistered);
    }
}