package io.filer.kalahservice.controller;

import io.filer.kalah.service.api.GamesApi;
import io.filer.kalah.service.model.GameStarted;
import io.filer.kalah.service.model.MoveResult;
import io.filer.kalahservice.service.KalahService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KalahController implements GamesApi {

    private KalahService kalahService;

    public KalahController(KalahService kalahService) {
        this.kalahService = kalahService;
    }

    @Override
    public ResponseEntity<GameStarted> startGame() {
        return new ResponseEntity<GameStarted>(kalahService.startGame(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MoveResult> makeMove(@PathVariable("gameid") String gameId, @PathVariable("pitid") String pitId) {
        return new ResponseEntity<MoveResult>(kalahService.makeMove(gameId, pitId), HttpStatus.OK);
    }
}
