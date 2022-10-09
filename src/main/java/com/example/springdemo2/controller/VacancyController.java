package com.example.springdemo2.controller;

import com.example.springdemo2.model.Vacancy;
import com.example.springdemo2.model.security.CustomerDetails;
import com.example.springdemo2.service.MainService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {
    private final MainService service;

    public VacancyController(MainService service) {
        this.service = service;
    }

    @GetMapping("/edit")
    private String getEditor(@RequestParam("selectedVacancy") String vacancyHashCode, Model model) {
        model.addAttribute("selectedVacancy", vacancyHashCode);
        return "vacancyEditor";
    }

    @PostMapping("/edit")
    private String editVacancy(@RequestParam("title") String title, @RequestParam("description") String description,
                               @RequestParam("requirements") String requirements,
                               @RequestParam("selectedVacancy") String vacancyHashCode) {
        Vacancy toReplace = service.getVacancyWithHash(vacancyHashCode);
        service.updateVacancy(toReplace, title, description, requirements);

        return "redirect:/main/vacancy/forEmployer";
    }

    @PostMapping("/delete")
    private String deleteVacancy(@RequestParam("selectedVacancy") String vacancyHashCode, Model model,
                                 @AuthenticationPrincipal CustomerDetails customerDetails) {
        service.deleteVacancy(service.getVacancyWithHash(vacancyHashCode));
        model.addAttribute("vacancies",
                service.getVacanciesForEmployer(service.getCustomer(customerDetails.getUsername())));
        return "employerVacancies";
    }

    @GetMapping("/create")
    private String getCreator(Model model, @AuthenticationPrincipal CustomerDetails customerDetails) {
        model.addAttribute("vacancies",
                service.getVacanciesForEmployer(service.getCustomer(customerDetails.getUsername())));
        return "vacancyCreator";
    }

    @PostMapping("/create")
    private String createNew(@RequestParam("title") String title, @RequestParam("description") String description,
                             @RequestParam("requirements") String requirements,
                             @AuthenticationPrincipal CustomerDetails customerDetails) {
        service.addVacancy(title, description, requirements,
                service.getCustomer(customerDetails.getUsername()));

        return "redirect:/main/vacancy/forEmployer";
    }
}
