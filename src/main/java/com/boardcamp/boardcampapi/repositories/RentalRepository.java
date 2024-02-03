package com.boardcamp.boardcampapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boardcamp.boardcampapi.models.RentalModel;

public interface RentalRepository extends JpaRepository<RentalModel, Long> {
    int countByGameId(Long gameId);
}
