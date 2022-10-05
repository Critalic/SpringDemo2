package com.example.springdemo2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class Customer {
    @NotNull
    @EqualsAndHashCode.Exclude
    private Role role;
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$",
            message = "Invalid e-mail format")
    private String email;
    @EqualsAndHashCode.Exclude
    private String name;

    public enum Role {

        EMPLOYEE("Employee"),
        EMPLOYER("Employer");

        private String name;
        Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
