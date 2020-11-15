package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.*;

import java.util.concurrent.ThreadLocalRandom;

public class Forest {
    public Cell[][] matrix;
    double spreadingProbability;

    ForestState forestState;
    DensityState densityState;
    SeasonState seasonState;
    WindState windState;
    WindForceState windForceState;
    RainfallState rainfallState;
    HumidityState humidityState;

    public Forest() {
    }

    public SeasonState getSeasonState() {
        return seasonState;
    }

    public double getSpreadingProbability() {
        return spreadingProbability;
    }

    public void setSpreadingProbability(double spreadingProbability) {
        this.spreadingProbability = spreadingProbability;
    }

    public void setupForest(ForestState forestState, DensityState densityState, SeasonState seasonState,
    WindState windState, WindForceState windForceState, RainfallState rainfallState, HumidityState humidityState) {

        this.forestState = forestState;
        this.densityState = densityState;
        this.seasonState = seasonState;
        this.windState = windState;
        this.windForceState = windForceState;
        this.rainfallState = rainfallState;
        this.humidityState = humidityState;

        plantTrees();

    }

    public CellState randomTree() {
        int random = ThreadLocalRandom.current().nextInt(0, 2);

        if (random == 0)
            return CellState.LeafyTree;
        else
            return CellState.ConiferTree;
    }

    public void plantTrees() {

        int trees;
        CellState treeType;

        if (forestState.equals(ForestState.Coniferous))
            treeType = CellState.ConiferTree;
        else
            treeType = CellState.LeafyTree;


        if (densityState.equals(DensityState.Poorly))
            trees = (matrix.length / 3) * (matrix[0].length/3);
        else if (densityState.equals(DensityState.Medium))
            trees = (matrix.length/4 * 3) * (matrix[0].length/4 * 3);
        else
            trees = (matrix.length/8 * 7) * (matrix[0].length/8 * 7);

        for (int i = 0; i < trees; i++) {
                int x = ThreadLocalRandom.current().nextInt(0, matrix.length);
                int y = ThreadLocalRandom.current().nextInt(0, matrix[0].length);

                if (!matrix[x][y].getState().equals(CellState.Lake)) {
                    if (!forestState.equals(ForestState.Mixed))
                        matrix[x][y].setState(treeType);
                    else
                        matrix[x][y].setState(randomTree());
                }
            }

    }

    public void deforestation() {
        for (Cell[] cells : matrix)
            for (int j = 0; j < matrix[0].length; j++)
                if (!cells[j].getState().equals(CellState.Lake))
                    cells[j].setState(CellState.Grass);
    }

}