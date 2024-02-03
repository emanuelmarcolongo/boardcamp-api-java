package com.boardcamp.boardcampapi.exceptions.GameExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GameExceptionHandler {
    @ExceptionHandler({ GameTitleConflictException.class })
    public ResponseEntity<String> handleGameTitleConflictException(GameTitleConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
