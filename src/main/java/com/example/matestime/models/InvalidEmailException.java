package com.example.matestime.models;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("Invalid Email format");
    }
}
