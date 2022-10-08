package com.example.springdemo2.service;

import com.example.springdemo2.model.CandidateRequirements;
import com.example.springdemo2.model.Customer;
import com.example.springdemo2.model.Vacancy;
import com.example.springdemo2.model.VacancyDescription;
import com.example.springdemo2.repository.JobSearchRepo;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class MainService {
    private final JobSearchRepo repo;

    public MainService(JobSearchRepo repo) {
        this.repo = repo;
    }

    public Customer getCustomer(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new ValidationException("User with given email wasn't found"));
    }

    public Vacancy getVacancyWithHash(String hashCode) {
        return repo.getVacancies().stream()
                .filter(vacancy1 -> vacancy1.hashCode() == Integer.parseInt(hashCode))
                .findFirst().orElseThrow(() -> new ValidationException("No vacancy with this id found"));
    }

    public void addCustomer(Customer customer) {
        if (repo.getCustomers().contains(customer)) {
            throw new ValidationException("Given customer is already present");
        }
        repo.addCustomer(customer);
    }

    public List<Vacancy> getVacancies() {
        return repo.getVacancies();
    }

    public List<Vacancy> getSearchedVacancies(String string) {
        return repo.getVacancies().stream()
                .filter(vacancy -> vacancy.getDescription().getText().contains(string) ||
                        vacancy.getDescription().getTitle().contains(string))
                .collect(Collectors.toList());
    }

    public List<Vacancy> getVacanciesForEmployer(Customer employer) {
        return repo.getVacancies().stream()
                .filter(vacancy -> vacancy.getOwner().getEmail().equals(employer.getEmail()))
                .collect(Collectors.toList());
    }

    public void deleteVacancy(Vacancy vacancy) {
        repo.deleteVacancy(vacancy);
    }

    public void addVacancy(String title, String description, String requirements, Customer customer) {
        Vacancy vacancy = new Vacancy(customer, new CandidateRequirements(Arrays.asList(
                requirements.split("\n"))),
                new VacancyDescription(title, description));
        if (repo.getVacancies().contains(vacancy)) {
            throw new ValidationException("Vacancy already exists");
        }
        repo.addVacancy(vacancy);
    }

    public void updateVacancy(Vacancy toReplace, String title, String description, String requirements) {
        if (!Stream.of(title, description, requirements).allMatch(Objects::isNull)) {
            repo.deleteVacancy(toReplace);
            if (!isBlank(title)) {
                toReplace.getDescription().setTitle(title);
            }
            if (!isBlank(description)) {
                toReplace.getDescription().setText(description);
            }
            if (!isBlank(requirements)) {
                toReplace.getRequirements().setRequirements(Arrays.asList(requirements.split("\n")));
            }
            repo.addVacancy(toReplace);
        }
    }
}
