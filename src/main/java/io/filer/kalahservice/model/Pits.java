package io.filer.kalahservice.model;

public class Pits {

    private int index;
    private int stones;

    private boolean isKalah;

    public Pits(int index) {
        this.stones = 6;
        this.index = index;

        if(index == 7 || index == 14) {
            this.isKalah = true;
            this.stones = 0;
        }
    }

    public int getStones() {
        return stones;
    }

    public void addStones() {
        this.stones = stones + 1;
    }

    public void removeStones(){
        this.stones = 0;
    }

    public boolean isKalah() {
        return isKalah;
    }
}
