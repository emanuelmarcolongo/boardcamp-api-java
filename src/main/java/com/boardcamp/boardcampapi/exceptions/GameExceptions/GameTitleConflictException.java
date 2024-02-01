package com.boardcamp.boardcampapi.exceptions.GameExceptions;

public class GameTitleConflictException extends RuntimeException {
    public GameTitleConflictException(String message) {
        super(message);
    }
}
