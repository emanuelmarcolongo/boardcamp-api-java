package com.boardcamp.boardcampapi.models;

import java.time.LocalDate;

import com.boardcamp.boardcampapi.DTOs.RentalDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {

    public RentalModel(RentalDTO rentalDTO, GameModel gameModel, CustomerModel customerModel) {
        this.daysRented = rentalDTO.getDaysRented();
        this.rentDate = LocalDate.now();
        this.delayFee = 0;
        this.originalPrice = gameModel.getPricePerDay() * rentalDTO.getDaysRented();
        this.customer = customerModel;
        this.game = gameModel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate rentDate;

    @Column(nullable = false)
    private int daysRented;

    @Column(nullable = true)
    private LocalDate returnDate;

    @Column(nullable = false)
    private int originalPrice;

    @Column
    private int delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameModel game;
}
