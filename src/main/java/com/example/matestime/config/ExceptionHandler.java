package com.example.matestime.config;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice                           //ukryty catch na wyjsciu dla Runtime
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public String notFound() {
        return "Not Found";
    }

    //missing dat exception dodatkowo


}
