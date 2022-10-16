package com.example.springdemo2.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ValidationException.class) //TODO
    public ModelAndView handleRateServiceValidationException(ValidationException e, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", e.getMessage());

        String[] path = request.getRequestURL().toString().split("/");
        modelAndView.setViewName(path[path.length - 1]);
        return modelAndView;
    }
}
