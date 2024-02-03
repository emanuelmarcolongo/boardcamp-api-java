package com.boardcamp.boardcampapi.exceptions.GameExceptions;

public class GameOutOfStockException extends RuntimeException {
    public GameOutOfStockException(String message) {
        super(message);
    }
}
