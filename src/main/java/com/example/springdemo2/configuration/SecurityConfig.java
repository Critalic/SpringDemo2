package com.example.springdemo2.configuration;

import com.example.springdemo2.model.security.CustomerDetails;
import com.example.springdemo2.repository.JobSearchRepo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.validation.ValidationException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(JobSearchRepo jobSearchRepo) {
        return email -> new CustomerDetails(jobSearchRepo.findByEmail(email)
                .orElseThrow(() -> new ValidationException("User with given email wasn't found")));
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.err.println(encoder.hashCode());
        return encoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/main/**").hasAnyRole("EMPLOYER", "EMPLOYEE")
                .antMatchers("/vacancy/**").hasRole("EMPLOYER")
                .antMatchers("/", "/**").permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/main", true)

                .and()
                .build();
    }

    @Override
    public void configure(WebSecurity web) {
        // Ignoring here is only for this example. Normally people would apply their own authentication/authorization policies
        web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");
    }
}
