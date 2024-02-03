package com.boardcamp.boardcampapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boardcamp.boardcampapi.DTOs.RentalDTO;
import com.boardcamp.boardcampapi.models.RentalModel;
import com.boardcamp.boardcampapi.services.RentalService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/rentals")
public class RentalController {
    final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    };

    @PostMapping()
    public ResponseEntity<RentalModel> addRental(@RequestBody @Valid RentalDTO rentalDTO) {
        RentalModel newRental = rentalService.addRental(rentalDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newRental);
    }

}
