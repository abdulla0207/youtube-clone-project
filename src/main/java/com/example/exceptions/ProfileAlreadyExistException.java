package com.example.exceptions;

public class ProfileAlreadyExistException extends RuntimeException{
    public ProfileAlreadyExistException(String message) {
        super(message);
    }
}
