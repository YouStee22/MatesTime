package com.example.matestime.models;

public class MissingDataException extends RuntimeException {
    public MissingDataException(String message) {
        super(message);
    }
}
