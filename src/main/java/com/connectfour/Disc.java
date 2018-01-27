package com.connectfour;

/**
 * Disc on the board could be in three states: EMPTY(not insert yet), RED, GREEN
 */
public enum Disc {
    EMPTY(" "),
    RED("R"),
    GREEN("G");

    private String sym;

    Disc(String sym) {
        this.sym = sym;
    }

    @Override
    public String toString() {
        return sym;
    }
}