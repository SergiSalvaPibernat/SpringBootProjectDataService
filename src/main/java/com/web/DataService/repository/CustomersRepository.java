package com.web.DataService.repository;

import com.web.DataService.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByNameAndPassword(String name, String password);
    Optional<Customer> findByEmail(String email);
}

