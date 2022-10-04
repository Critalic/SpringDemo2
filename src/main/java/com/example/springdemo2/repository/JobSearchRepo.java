package com.example.springdemo2.repository;

import com.example.springdemo2.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class JobSearchRepo {
    private HashSet<Vacancy> vacancies;

    public List<Vacancy> getVacancies() {
        return new ArrayList<>(vacancies);
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    public void deleteVacancy(Vacancy vacancy) {
        vacancies.remove(vacancy);
    }
}
