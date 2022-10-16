package com.example.springdemo2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class Vacancy {
    @NotNull
    @EqualsAndHashCode.Exclude
    private Customer owner;
    @NotEmpty
    private List<Requirement> requirements;
    @NotNull
    private VacancyDescription description;

    public Vacancy(Customer owner, List<Requirement> requirements, VacancyDescription description) {
        this.owner = owner;
        this.requirements = requirements;
        this.description = description;
    }
}
