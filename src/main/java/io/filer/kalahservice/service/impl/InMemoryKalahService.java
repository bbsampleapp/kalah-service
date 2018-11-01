package io.filer.kalahservice.service.impl;

import io.filer.kalah.service.model.GameStarted;
import io.filer.kalah.service.model.MoveResult;
import io.filer.kalahservice.service.KalahService;
import org.springframework.stereotype.Component;

@Component
public class InMemoryKalahService implements KalahService {

    @Override
    public GameStarted startGame() {
        GameStarted gameStarted = new GameStarted();
        gameStarted.setId("1234");
        gameStarted.setUrl("http://localhost:8080/games/1234");
        return gameStarted;
    }

    @Override
    public MoveResult makeMove(String gameId, String pitId) {
        MoveResult moveResult = new MoveResult();
        moveResult.setState("1, 2, 3, 4, 5");
        moveResult.setUrl("http://localhost:8080/games/1234");
        moveResult.setId("1234");
        return moveResult;
    }
}
