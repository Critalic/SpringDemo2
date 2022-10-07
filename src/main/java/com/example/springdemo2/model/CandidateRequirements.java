package com.example.springdemo2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class CandidateRequirements {
    @NotEmpty
    private List<String> requirements;

    public CandidateRequirements(List<String> requirements) {
        this.requirements = requirements;
    }
}
