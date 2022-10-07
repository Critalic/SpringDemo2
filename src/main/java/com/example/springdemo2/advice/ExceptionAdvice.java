package com.example.springdemo2.advice;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ValidationException.class) //TODO
    public void handleRateServiceValidationException(ValidationException e, HttpServletRequest request,
                                                             HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", e.getMessage());
        response.sendRedirect(request.getRequestURL().toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleRateServiceValidationException(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder("Record API error: ");
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(error.getDefaultMessage()).append("\n");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}
