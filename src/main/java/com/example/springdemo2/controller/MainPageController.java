package com.example.springdemo2.controller;

import com.example.springdemo2.model.security.CustomerDetails;
import com.example.springdemo2.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainPageController {

    private MainService service;

    @Autowired
    public void setService(MainService service) {
        this.service = service;
    }

    @GetMapping
    public String getMainPage(@AuthenticationPrincipal CustomerDetails customerDetails, Model model) {
        model.addAttribute("customer", service.getCustomer(customerDetails.getUsername()));
        model.addAttribute("vacancies", service.getVacancies());
        return "main";
    }

    @GetMapping("/vacancy/search")
    public String getVacancy(@RequestParam("desription") String search, Model model,
                             @AuthenticationPrincipal CustomerDetails customerDetails) {
        model.addAttribute("vacancies", service.getSearchedVacancies(search.trim().toLowerCase()));
        model.addAttribute("customer", service.getCustomer(customerDetails.getUsername()));
        return "main";
    }

    @GetMapping("/vacancy/forEmployer")
    public String getVacancyForEmployee(@AuthenticationPrincipal CustomerDetails customerDetails, Model model) {
        model.addAttribute("vacancies",
                service.getVacanciesForEmployer(service.getCustomer(customerDetails.getUsername())));
        return "employerVacancies";
    }

}
