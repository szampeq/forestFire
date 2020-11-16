package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.CellState;

public class Cell {

    CellState state;
    double inflammability;
    double burningTime = 15.0;

    public Cell(CellState state) {
        this.state = state;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
        setInflammability();
    }

    public void setInflammability() {
        if (state == CellState.Lake)
            this.inflammability = 0;
        else if (state == CellState.Grass)
            this.inflammability = 0.4;
        else if (state == CellState.LeafyTree)
            this.inflammability = 0.5;
        else if (state == CellState.ConiferTree)
            this.inflammability = 0.8;
        else if (state == CellState.BurningTree)
            this.inflammability = 1;
        else if (state == CellState.BurnedTree)
            this.inflammability = 0;
        else
            this.inflammability = 0;
    }

    void isBurned() {
        if (burningTime > 0)
            burningTime--;
        else if (burningTime == 0)
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