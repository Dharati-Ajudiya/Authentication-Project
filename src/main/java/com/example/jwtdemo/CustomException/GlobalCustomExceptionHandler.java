package com.example.jwtdemo.CustomException;

import com.example.jwtdemo.CustomException.ExceptionResponse;
import com.example.jwtdemo.CustomException.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalCustomExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleExceptions() {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Username or password cannot be null");
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

}
