package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.CellState;

public class Cell {

    CellState state;
    double burningTime;

    public Cell(CellState state) {
        this.state = state;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
        setInitialTime();
    }

    public void setInitialTime() {
        if (state == CellState.Grass)
            this.burningTime = 6;
        else if (state == CellState.LeafyTree)
            this.burningTime = 25;
        else if (state == CellState.ConiferTree)
            this.burningTime = 15;
    }

    void isBurned() {
        if (state.equals(CellState.BurningTree) && burningTime > 0) {
            burningTime--;
        }
        else if (state.equals(CellState.BurningTree) && burningTime <= 0)
            state = CellState.BurnedTree;
    }

    public void setBurningTime(double burningTime) {
        this.burningTime = burningTime;
    }

    public double getBurningTime() {
        return burningTime;
    }

    boolean isFlammable() {
        return state.equals(CellState.Grass) || state.equals(CellState.LeafyTree) || state.equals(CellState.ConiferTree);
    }
}