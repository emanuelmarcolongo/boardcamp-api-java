package com.boardcamp.boardcampapi.UnitTests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.boardcampapi.repositories.GameRepository;
import com.boardcamp.boardcampapi.services.GameService;

@SpringBootTest

public class GamesUnitTests {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void givenValidGame_whenCreatingGame_thenCreatesGame() {

    }

}
