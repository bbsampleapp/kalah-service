package io.filer.kalahservice.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KalahBoard {

    Pits[] playerPitsArray = {new Pits(1), new Pits(2), new Pits(3), new Pits(4), new Pits(5), new Pits(6),
            new Pits(7), new Pits(8), new Pits(9), new Pits(10), new Pits(11), new Pits(12), new Pits(13),
            new Pits(14)};

    private List<Pits> pits;

    public KalahBoard() {
        this.pits = Collections.synchronizedList(new ArrayList<>());
        this.pits.addAll(Arrays.asList(playerPitsArray));
    }

    public KalahBoard(List<Pits> pits) {
        this.pits = pits;
    }

    public List<Pits> getPits() {
        return pits;
    }

}

