package com.web.DataService.repository;

import com.web.DataService.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

}

