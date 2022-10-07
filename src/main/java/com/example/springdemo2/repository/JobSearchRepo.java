package com.example.springdemo2.repository;

import com.example.springdemo2.model.Customer;
import com.example.springdemo2.model.Vacancy;
import org.springframework.stereotype.Repository;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class JobSearchRepo {
    private final HashSet<Vacancy> vacancies = new HashSet<>();
    private final HashSet<Customer> customers = new HashSet<>();

    public List<Vacancy> getVacancies() {
        return new ArrayList<>(vacancies);
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    public void deleteVacancy(Vacancy vacancy) {
        vacancies.remove(vacancy);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public Optional<Customer> findByEmail(String email) {
        return customers.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

}
