package com.example.springdemo2;

import com.example.springdemo2.model.security.RegistrationForm;
import com.example.springdemo2.repository.JobSearchRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringDemo2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringDemo2Application.class, args);
        JobSearchRepo repo = context.getBean(JobSearchRepo.class);
        repo.addCustomer(new RegistrationForm(
                "jaiojdi@gmail.com", "ROLE_EMPLOYER", "Martha", "2335")
                .toUser(context.getBean(PasswordEncoder.class)));
        repo.addCustomer(new RegistrationForm(
                "jdi@gmail.com", "ROLE_EMPLOYEE", "Martin", "2335")
                .toUser(context.getBean(PasswordEncoder.class)));
    }
}
