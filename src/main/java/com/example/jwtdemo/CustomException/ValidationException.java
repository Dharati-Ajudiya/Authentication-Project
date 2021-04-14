package com.example.jwtdemo.CustomException;

public class ValidationException extends RuntimeException {

    public ValidationException(String s) {
        super(s);
    }
}
