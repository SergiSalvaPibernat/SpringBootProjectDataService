package com.web.DataService.api;

import java.util.Optional;

import com.web.DataService.domain.LoginRequest;
import com.web.DataService.domain.RegisterRequest;
import com.web.DataService.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.web.DataService.domain.Customer;
import com.web.DataService.service.CustomersService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerAPI {

    @Autowired
    CustomersService service;

    /*@GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        Iterable<Customer> customers = service.findAllCustomers();
        System.out.println("Username " +customers.iterator().next().getName());
        System.out.println("Password " + customers.iterator().next().getPassword());
        System.out.println("Email " + customers.iterator().next().getEmail());
        /*if (customers == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }*/
    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        try {
            Iterable<Customer> customers = service.findAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*@GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        Customer customer = service.findCustomerById(id).orElse(null);
        return ResponseEntity.ok(customer);
    }*/
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        try {
            Optional<Customer> customerOptional = service.findCustomerById(id);
            if (customerOptional.isPresent()) {
                return ResponseEntity.ok(customerOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error retrieving customer: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*@PutMapping("/customers/{id}")
    public ResponseEntity<Customer> putCustomer(@PathVariable long id, @RequestBody Customer customer){
        try{
            Customer custom = service.updateCustomer(id, customer);
            return ResponseEntity.ok(custom);
        } catch (RuntimeException r){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> putCustomer(@PathVariable long id, @RequestBody Customer customer){
        try {
            Customer updatedCustomer = service.updateCustomer(id, customer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            System.out.println("Customer not found for update: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*@PostMapping("/customers")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
        try{
            service.saveCustomer(customer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @PostMapping("/customers")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
        try {
            Customer savedCustomer = service.saveCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /*@DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
        try{
            service.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") long id){
        try {
            service.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            System.out.println("Customer not found for deletion: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/customers/validate")
    public ResponseEntity<Boolean> getCustomerValidation(@RequestBody LoginRequest loginRequest) {
        System.out.println("Username " +loginRequest.getName());
        System.out.println("Password " + loginRequest.getPassword());
        boolean isCustomerValid = service.getCustomerValidation(loginRequest);
        return ResponseEntity.ok(isCustomerValid);
    }

    @PostMapping("/customers/validateRegister")
    public ResponseEntity<Boolean> getCustomerValidationRegister(@RequestBody RegisterRequest request) {
        boolean isCustomerValid = service.getCustomerValidationRegister(request);
        return ResponseEntity.ok(isCustomerValid);
    }

    @GetMapping("/")
    public String health() {
        return "Resource Server is running.";
    }
}
