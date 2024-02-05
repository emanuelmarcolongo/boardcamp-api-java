package com.boardcamp.boardcampapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boardcamp.boardcampapi.models.RentalModel;

public interface RentalRepository extends JpaRepository<RentalModel, Long> {
    int countByGameId(Long gameId);

    @Query(value = "SELECT COUNT(r) FROM rentals r WHERE r.game_id = :gameId AND r.return_date IS NULL", nativeQuery = true)
    int countGameRentalsAmount(@Param("gameId") Long gameId);
}
