package com.example.springdemo2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class Requirement {
    @NotEmpty
    private String requirementText;

    public Requirement(String requirements) {
        this.requirementText = requirements;
    }
}
