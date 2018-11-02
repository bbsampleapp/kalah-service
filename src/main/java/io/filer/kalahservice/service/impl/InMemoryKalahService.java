package io.filer.kalahservice.service.impl;

import io.filer.kalah.service.model.GameStarted;
import io.filer.kalah.service.model.MoveResult;
import io.filer.kalahservice.model.DataHolder;
import io.filer.kalahservice.model.KalahBoard;
import io.filer.kalahservice.model.Pits;
import io.filer.kalahservice.service.KalahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InMemoryKalahService implements KalahService {

    private final DataHolder dataHolder;

    @Value("${kalah.base.uri}")
    private String baseUri;

    @Autowired
    public InMemoryKalahService (DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Override
    public GameStarted startGame() {
        Map<String, KalahBoard> games = dataHolder.getGameData();

        String gameId = UUID.randomUUID().toString();
        games.put(gameId, new KalahBoard());

        GameStarted gameStarted = new GameStarted();
        gameStarted.setId(gameId);
        gameStarted.setUrl(baseUri + "/games/" + gameId);
        return gameStarted;
    }

    @Override
    public MoveResult makeMove(String gameId, String pitId) {

        KalahBoard kalahBoard = null;
         if (dataHolder.getGameData().get(gameId) == null) {
             throw new RuntimeException("Cannot find game for gameid " + gameId);
         } else {
             kalahBoard = dataHolder.getGameData().get(gameId);
         }

        doMove(kalahBoard.getPits(), Integer.valueOf(pitId), 0);

        //Overwrite state
        dataHolder.setGameData(gameId, new KalahBoard(kalahBoard.getPits()));

        String state = kalahBoard.getPits()
                .stream()
                .map(pits -> String.valueOf(pits.getStones()))
                .collect(Collectors.joining(","));

        MoveResult moveResult = new MoveResult();
        moveResult.setState(state);
        moveResult.setUrl(baseUri + "/games/" + gameId);
        moveResult.setId(gameId);
        return moveResult;
    }

    private void doMove(List<Pits> pits, int choosenStartPit, int stonesUsed) {
        //TODO improve this algorithm.  Also not all rules have been implemented.
        Pits startingHome = pits.get(choosenStartPit);

        if(startingHome.getStones() > 0){
            int startingHomeStones = 0;
            if(stonesUsed > 0) {
                startingHomeStones = startingHome.getStones() - stonesUsed;
            } else {
                startingHomeStones = startingHome.getStones();
                startingHome.removeStones();
            }

            int stonesUsedIndex = 0;
            for( int i = choosenStartPit == 0 ? 0 : choosenStartPit + 1; i < (choosenStartPit + startingHomeStones); i++ ){
                if(i == 14) {
                    doMove(pits, 0, stonesUsedIndex);
                    return;
                }

                Pits home = pits.get(i);
                home.addStones();
                stonesUsedIndex++;
            }
        }
    }

}
