package io.filer.kalahservice.model;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataHolder {

    private Map<String, KalahBoard> gameData;

    public DataHolder() {
        gameData = Collections.synchronizedMap(new HashMap<>());
    }

    public void setGameData(String key, KalahBoard value) {
        this.gameData.put(key, value);
    }

    public Map<String, KalahBoard> getGameData() {
        return gameData;
    }
}
