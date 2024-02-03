package com.boardcamp.boardcampapi.exceptions.GameExceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String message) {
        super(message);
    }
}
