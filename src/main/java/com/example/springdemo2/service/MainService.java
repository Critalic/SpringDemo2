package com.example.springdemo2.service;

import com.example.springdemo2.model.Customer;
import com.example.springdemo2.repository.JobSearchRepo;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class MainService {
    private JobSearchRepo repo;

    public MainService(JobSearchRepo repo) {
        this.repo = repo;
    }

    public Customer getCustomer(String email) {
        return repo.getCustomers().stream()
                .filter(e -> e.getEmail().equals(email))
                .findFirst().orElseThrow(ValidationException::new);
    }
 }
