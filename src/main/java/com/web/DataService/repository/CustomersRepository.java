package com.web.DataService.repository;

import com.web.DataService.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

    //Optional<Customer> findByUsernameAndPassword(String name, String password);
    Optional<Customer> findByUsernameAndPassword(String username, String password);
    Optional<Customer> findByEmail(String email);
}

