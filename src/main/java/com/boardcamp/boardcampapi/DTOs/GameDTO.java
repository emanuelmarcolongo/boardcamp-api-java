package com.boardcamp.boardcampapi.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {

    @NotBlank(message = "Game must have a name")
    private String name;

    @Pattern(regexp = "^(http|https)://.*$", message = "Image must start with 'http://' or 'https://'")
    private String image;

    @NotNull
    @Positive(message = "StockTotal must be a positive value")
    private int stockTotal;

    @NotNull
    @Positive(message = "Price per day must be a positive value")
    private int pricePerDay;
}
