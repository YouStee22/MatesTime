package com.example.matestime.config;


import com.example.matestime.models.InvalidEmailException;
import com.example.matestime.models.MissingDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice                           //ukryty catch na wyjsciu dla Runtime
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(MissingDataException.class)
    public String notFound(MissingDataException e) {
        return e.getMessage();         //payload
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidEmailException.class)
    public String invalidEmail(InvalidEmailException e) {
        return e.getMessage();         //payload
    }


    //invalid data eception?

}
