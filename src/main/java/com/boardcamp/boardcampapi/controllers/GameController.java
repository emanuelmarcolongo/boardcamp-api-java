package com.boardcamp.boardcampapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boardcamp.boardcampapi.DTOs.GameDTO;
import com.boardcamp.boardcampapi.models.GameModel;
import com.boardcamp.boardcampapi.services.GameService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/games")
public class GameController {

    final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping()
    public ResponseEntity<Object> addGame(@RequestBody @Valid GameDTO gameDTO) {

        GameModel newGame = gameService.addGame(gameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @GetMapping()
    public ResponseEntity<List<GameModel>> getAllGames() {
        List<GameModel> allGames = gameService.getGames();

        return ResponseEntity.status(HttpStatus.OK).body(allGames);
    }

}
