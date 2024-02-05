package com.boardcamp.boardcampapi.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping()
    public ResponseEntity<List<RentalModel>> getRentals() {
        List<RentalModel> rentals = rentalService.getRentals();

        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<RentalModel> returnRental(@PathVariable Long id) {

        RentalModel rental = rentalService.returnRental(id);
        return ResponseEntity.status(HttpStatus.OK).body(rental);
    }

}
