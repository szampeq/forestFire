package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.CellState;
import com.krzysztgac.forestfire.states.ForestState;

import java.util.concurrent.ThreadLocalRandom;

import static com.krzysztgac.forestfire.Main.forestData;

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
        else if (state.equals(CellState.BurnedTree) && burningTime > -1000)
            burningTime--;
        else if (state.equals(CellState.BurnedTree) && burningTime <= -1000) {
            plantTree();
        }
    }

    void plantTree() {
        int random = ThreadLocalRandom.current().nextInt(0, 10);
        switch (forestData.getForestState()) {
            case Coniferous:
                if (random == 0)
                    setState(CellState.ConiferTree);
                else
                    setState(CellState.Grass);
                break;
            case Deciduous:
                if (random == 0)
                    setState(CellState.LeafyTree);
                else
                    setState(CellState.Grass);
                break;
            default:
                if (random == 0)
                    setState(CellState.LeafyTree);
                else if (random == 1)
                    setState(CellState.ConiferTree);
                else
                    setState(CellState.Grass);
                break;
        }
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