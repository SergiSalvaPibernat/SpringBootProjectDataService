package com.web.DataService.service;

import java.util.Optional;

import com.web.DataService.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.DataService.repository.CustomersRepository;

@Service
public class CustomerServiceImpl implements CustomersService{

    @Autowired
    private CustomersRepository repo;

    @Override
    public Iterable<Customer> findAllCustomers() {
        return repo.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(long id) {
        return repo.findById(id);
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {

        Customer custom = repo.findById(id).orElseThrow(()->new RuntimeException("Customer not found with id"));
        custom.setEmail(customer.getEmail());
        custom.setName(customer.getName());
        custom.setPassword(customer.getPassword());
        return repo.save(custom);
    }

    @Override
    public void saveCustomer(Customer customer) {
        repo.save(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        Customer custom = repo.findById(id).orElseThrow(()->new RuntimeException("Customer not found with id"));
        repo.deleteById(id);
    }

}
