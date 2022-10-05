package com.example.springdemo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("customer")
public class InitialController {

    @GetMapping
    public String getLoginPage() {
        return "LoginView";
    }

    @PostMapping("/login")
    public String getMainPage(HttpSession session,
                              Model model,
                              @RequestParam("email") String email) {
        return "LoginView";
    }
}
