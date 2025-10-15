package com.web.DataService.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.DataService.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

}

