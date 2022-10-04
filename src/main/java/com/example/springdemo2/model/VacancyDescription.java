package com.example.springdemo2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class VacancyDescription {
    @EqualsAndHashCode.Exclude
    private Vacancy vacancy;
    @NotEmpty
    private String description;
}
