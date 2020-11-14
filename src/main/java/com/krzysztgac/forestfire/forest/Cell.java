package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.CellState;

public class Cell {

    CellState state;

    public Cell(CellState state) {
        this.state = state;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }
}