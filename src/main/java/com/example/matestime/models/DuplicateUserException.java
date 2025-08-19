package com.example.matestime.models;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {
        super("User already exists");
    }
}
