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
@CrossOrigin
public class CustomerAPI {

    @Autowired
    CustomersService service;

    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        Iterable<Customer> customers = service.findAllCustomers();
        System.out.println("Username " +customers.iterator().next().getName());
        System.out.println("Password " + customers.iterator().next().getPassword());
        System.out.println("Email " + customers.iterator().next().getEmail());
        if (customers == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {

        Customer customer = service.findCustomerById(id).orElse(null);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> putCustomer(@PathVariable long id, @RequestBody Customer customer){
        try{
            Customer custom = service.updateCustomer(id, customer);
            return ResponseEntity.ok(custom);
        } catch (RuntimeException r){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
        try{
            service.saveCustomer(customer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
        try{
            service.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
