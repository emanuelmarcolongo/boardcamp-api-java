package com.boardcamp.boardcampapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.boardcampapi.DTOs.GameDTO;
import com.boardcamp.boardcampapi.exceptions.GameExceptions.GameTitleConflictException;
import com.boardcamp.boardcampapi.models.GameModel;
import com.boardcamp.boardcampapi.repositories.GameRepository;

@Service
public class GameService {

    final GameRepository gameRepository;

    GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameModel addGame(GameDTO gameDTO) {

        boolean gameExists = gameRepository.existsByName(gameDTO.getName());
        if (gameExists) {
            throw new GameTitleConflictException("Game with given name already exists");
        }

        GameModel newGame = new GameModel(gameDTO);

        return gameRepository.save(newGame);

    }

    public List<GameModel> getGames() {
        return gameRepository.findAll();
    }
}
