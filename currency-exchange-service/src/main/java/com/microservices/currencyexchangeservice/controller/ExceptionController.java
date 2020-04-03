package com.microservices.currencyexchangeservice.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(UnsupportedOperationException.class)
    public String handleErrors(UnsupportedOperationException ex) {
        return ex.getMessage();
    }

}
