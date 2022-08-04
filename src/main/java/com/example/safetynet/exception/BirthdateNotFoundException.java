package com.example.safetynet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BirthdateNotFoundException extends RuntimeException {

    public BirthdateNotFoundException(String message) {
        super(message);
    }
}
