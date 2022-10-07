package com.example.springdemo2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Vacancy {
    @NotNull
    @EqualsAndHashCode.Exclude
    private Customer owner;
    @NotNull
    private CandidateRequirements requirements;
    @NotNull
    private VacancyDescription description;

    public Vacancy(Customer owner, CandidateRequirements requirements, VacancyDescription description) {
        this.owner = owner;
        this.requirements = requirements;
        this.description = description;
    }
}
