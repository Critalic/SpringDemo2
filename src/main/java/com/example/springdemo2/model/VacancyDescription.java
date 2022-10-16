package com.example.springdemo2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class VacancyDescription {
    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    public VacancyDescription(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
