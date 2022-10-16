package com.example.springdemo2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class Customer {
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$",
            message = "Invalid e-mail format")
    private String email;

    @EqualsAndHashCode.Exclude
    private String role;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private String password;

    public Customer(String email, String role, String name, String password) {
        this.email = email;
        this.role = role;
        this.name = name;
        this.password = password;
    }
}
