package io.filer.kalahservice.service;

import io.filer.kalah.service.model.GameStarted;
import io.filer.kalah.service.model.MoveResult;

public interface KalahService {

    /**
     * Initialise the game board
     * @return GameStarted the details of the game that has been created
     */
    GameStarted startGame();

    /**
     * Take a turn by choosing the correct game in progress and pitid to start from
     * @return MoveResult the current state of the game
     */
    MoveResult makeMove(String gameId, String pitId);

}
