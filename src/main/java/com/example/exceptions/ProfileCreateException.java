package com.example.exceptions;

import java.time.temporal.TemporalUnit;

public class ProfileCreateException extends RuntimeException {
    public ProfileCreateException(String message) {
        super(message);
    }
}
