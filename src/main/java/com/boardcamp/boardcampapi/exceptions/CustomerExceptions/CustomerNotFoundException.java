package com.boardcamp.boardcampapi.exceptions.CustomerExceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
