package com.example.springdemo2.model.security;

import com.example.springdemo2.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class RegistrationForm {
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$",
            message = "Invalid e-mail format")
    private String email;

    @NotEmpty
    private String role;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    public RegistrationForm(String email, String role, String name, String password) {
        this.email = email;
        this.role = role;
        this.name = name;
        this.password = password;
    }

    public Customer toUser(PasswordEncoder passwordEncoder) {
        return new Customer(email, role, name, passwordEncoder.encode(password));
    }
}
