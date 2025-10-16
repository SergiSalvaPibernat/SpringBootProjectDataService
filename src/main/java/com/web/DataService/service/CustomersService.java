package com.web.DataService.service;

import java.util.Optional;

import com.web.DataService.domain.Customer;
import com.web.DataService.domain.LoginRequest;

public interface CustomersService {

	public Iterable<Customer> findAllCustomers();
	public Optional<Customer> findCustomerById(long id);
	public Customer updateCustomer(long id, Customer customer);
	public void saveCustomer(Customer customer);
	public void deleteCustomer(long id);
	public boolean getCustomerValidation(LoginRequest loginRequest);
}
