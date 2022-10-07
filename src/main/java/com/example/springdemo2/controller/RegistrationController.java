package com.example.springdemo2.controller;

import com.example.springdemo2.model.security.RegistrationForm;
import com.example.springdemo2.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private MainService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return "signup";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        service.addCustomer(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
