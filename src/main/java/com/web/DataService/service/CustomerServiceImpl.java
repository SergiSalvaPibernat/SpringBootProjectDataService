package com.web.DataService.service;

import java.util.Optional;

import com.web.DataService.domain.Customer;
import com.web.DataService.domain.LoginRequest;
import com.web.DataService.domain.RegisterRequest;
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
        Customer custom = repo.findByEmail(customer.getEmail()).orElse(null);

        if(custom == null) {
            repo.save(customer);
        }
        else{
            throw new RuntimeException();
        }

    }

    @Override
    public void deleteCustomer(long id) {
        Customer custom = repo.findById(id).orElseThrow(()->new RuntimeException("Customer not found with id"));
        repo.deleteById(id);
    }

    @Override
    public boolean getCustomerValidation(LoginRequest loginRequest) {

        Customer customer = repo.findByUsernameAndPassword(loginRequest.getName()
        ,loginRequest.getPassword()).orElse(null);

        return customer != null;
    }

    @Override
    public boolean getCustomerValidationRegister(RegisterRequest registerRequest) {
        Customer customer = repo.findByEmail(registerRequest.getEmail()).orElse(null);
        System.out.println(registerRequest.toString());
        System.out.println(customer);
        if(customer == null){
            Customer cust = new Customer();
            cust.setPassword(registerRequest.getPassword());
            cust.setName(registerRequest.getName());
            cust.setEmail(registerRequest.getEmail());
            saveCustomer(cust);
            return true;
        }
        else{
            return false;
        }

    }

}
