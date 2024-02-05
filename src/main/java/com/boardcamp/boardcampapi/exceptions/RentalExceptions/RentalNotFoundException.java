package com.boardcamp.boardcampapi.exceptions.RentalExceptions;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String message) {
        super(message);
    }
}
