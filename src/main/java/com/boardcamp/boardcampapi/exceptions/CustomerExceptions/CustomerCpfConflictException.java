package com.boardcamp.boardcampapi.exceptions.CustomerExceptions;

public class CustomerCpfConflictException extends RuntimeException {
    public CustomerCpfConflictException(String message) {
        super(message);
    }
}
