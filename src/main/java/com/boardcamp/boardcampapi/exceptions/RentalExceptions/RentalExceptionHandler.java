package com.boardcamp.boardcampapi.exceptions.RentalExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RentalExceptionHandler {

    @ExceptionHandler({ RentalNotFoundException.class })
    public ResponseEntity<String> handleRentalNotFoundException(RentalNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ RentalAlreadyReturnedException.class })
    public ResponseEntity<String> handleRentalAlreadyReturnedException(RentalAlreadyReturnedException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
