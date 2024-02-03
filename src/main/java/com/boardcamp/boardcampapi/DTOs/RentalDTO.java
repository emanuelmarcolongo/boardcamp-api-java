package com.boardcamp.boardcampapi.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalDTO {

    @Positive(message = "Days rented must be a positive number")
    private int daysRented;

    @NotNull
    @Positive(message = "CustomerId must be a postive number")
    private Long customerId;

    @NotNull
    @Positive(message = "GameId must be a postive number")
    private Long gameId;

}
