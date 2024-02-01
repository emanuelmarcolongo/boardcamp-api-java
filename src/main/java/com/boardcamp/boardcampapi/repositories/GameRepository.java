package com.boardcamp.boardcampapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardcamp.boardcampapi.models.GameModel;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {
    boolean existsByName(String name);
}
