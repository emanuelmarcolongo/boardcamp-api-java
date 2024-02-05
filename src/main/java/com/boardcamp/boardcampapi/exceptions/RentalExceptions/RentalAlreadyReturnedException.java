package com.boardcamp.boardcampapi.exceptions.RentalExceptions;

public class RentalAlreadyReturnedException extends RuntimeException {
    public RentalAlreadyReturnedException(String message) {
        super(message);
    }

}
