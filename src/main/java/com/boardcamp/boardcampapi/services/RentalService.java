package com.boardcamp.boardcampapi.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.boardcampapi.DTOs.RentalDTO;
import com.boardcamp.boardcampapi.exceptions.CustomerExceptions.CustomerNotFoundException;
import com.boardcamp.boardcampapi.exceptions.GameExceptions.GameNotFoundException;
import com.boardcamp.boardcampapi.exceptions.GameExceptions.GameOutOfStockException;
import com.boardcamp.boardcampapi.exceptions.RentalExceptions.RentalAlreadyReturnedException;
import com.boardcamp.boardcampapi.exceptions.RentalExceptions.RentalNotFoundException;
import com.boardcamp.boardcampapi.models.CustomerModel;
import com.boardcamp.boardcampapi.models.GameModel;
import com.boardcamp.boardcampapi.models.RentalModel;
import com.boardcamp.boardcampapi.repositories.CustomerRepository;
import com.boardcamp.boardcampapi.repositories.GameRepository;
import com.boardcamp.boardcampapi.repositories.RentalRepository;

@Service
public class RentalService {

    final RentalRepository rentalRepository;
    final CustomerRepository customerRepository;
    final GameRepository gameRepository;

    public RentalService(RentalRepository rentalRepository, CustomerRepository customerRepository,
            GameRepository gameRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.gameRepository = gameRepository;
    }

    public RentalModel addRental(RentalDTO rentalDTO) {
        CustomerModel customer = customerRepository.findById(rentalDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("No customer with given Id"));

        GameModel game = gameRepository.findById(rentalDTO.getGameId())
                .orElseThrow(() -> new GameNotFoundException("No game with given Id"));

        int gameRentals = rentalRepository.countGameRentalsAmount(rentalDTO.getGameId());

        if (gameRentals >= game.getStockTotal()) {
            throw new GameOutOfStockException("All copies of this game are already rented");
        }

        RentalModel rental = new RentalModel(rentalDTO, game, customer);
        return rentalRepository.save(rental);
    }

    public List<RentalModel> getRentals() {
        List<RentalModel> rentals = rentalRepository.findAll();

        return rentals;
    }

    public RentalModel returnRental(Long id) {
        RentalModel rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException("Rental with given id does not exists."));

        if (rental.getReturnDate() != null) {
            throw new RentalAlreadyReturnedException("This rental has already been returned");
        }

        GameModel gameRented = rental.getGame();

        Long daysWithGame = ChronoUnit.DAYS.between(rental.getRentDate(), LocalDate.now());

        if (daysWithGame > rental.getDaysRented()) {
            int delayFee = (int) (daysWithGame - rental.getDaysRented()) * gameRented.getPricePerDay();
            rental.setDelayFee(delayFee);
        }

        rental.setReturnDate(LocalDate.now());

        rentalRepository.save(rental);
        return rental;
    }

}
