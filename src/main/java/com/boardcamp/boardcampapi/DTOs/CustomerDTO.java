package com.boardcamp.boardcampapi.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotBlank(message = "Customer name is required")
    private String name;

    @NotBlank
    @Size(max = 11, min = 11, message = "CPF must contain 11 characters")
    private String cpf;
}
