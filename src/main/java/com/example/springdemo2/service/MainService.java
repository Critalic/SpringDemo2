package com.example.springdemo2.service;

import com.example.springdemo2.model.Customer;
import com.example.springdemo2.repository.JobSearchRepo;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class MainService {
    private final JobSearchRepo repo;

    public MainService(JobSearchRepo repo) {
        this.repo = repo;
    }

    public Customer getCustomer(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new ValidationException("User with given email wasn't found"));
    }

    public void addCustomer(Customer customer) {
        if(repo.getCustomers().contains(customer)) {
            throw new ValidationException("Given customer is already present");
        }
        repo.addCustomer(customer);
    }
 }
