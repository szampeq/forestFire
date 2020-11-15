package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.*;

import java.util.concurrent.ThreadLocalRandom;

public class Forest {
    public Cell[][] matrix;
    double spreadingFireFactor;
    double burningTimeFactor;

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

    public double getSpreadingFireFactor() {
        return spreadingFireFactor;
    }

    public void setSpreadingFireFactor(double spreadingFireFactor) {
        this.spreadingFireFactor = spreadingFireFactor;
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

    public void setFire(int x, int y) {

        for (int i = -3; i < 3; i++)
            for (int j = -3; j < 3; j++) {

                if (x + i < 0 || y + i < 0) continue;
                if (x + i > matrix.length - 1 || y + j > matrix[0].length - 1) continue;

                if (!matrix[x+i][y+j].getState().equals(CellState.Lake)) {
                    matrix[x + i][y + j].setState(CellState.BurningTree);
                    matrix[x + i][y + j].setBurningTime(10);
                }
            }
    }

    public void cellNeighborhood() {

        Cell[][] newCells = new Cell[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                newCells[i][j] = new Cell(CellState.None);
                newCells[i][j].setBurningTime(matrix[i][j].getBurningTime() - 1);
            }


        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {

                int burningNeighbors = 0;

                for (int m = -1; m <= 1; m++)
                    for (int n = -1; n <= 1; n++) {

                        int x = i + m;
                        int y = j + n;

                        if (x == i && y == j) continue;
                        if (x < 0 || y < 0) continue;
                        if (x > matrix.length - 1 || y > matrix[0].length - 1) continue;

                        if (matrix[x][y].getState().equals(CellState.BurningTree))
                            burningNeighbors++;
                    }

                if (burningNeighbors > 0 && !matrix[i][j].getState().equals(CellState.Lake) &&
                        !matrix[i][j].getState().equals(CellState.BurnedTree) && !matrix[i][j].getState().equals(CellState.BurningTree)) {
                    newCells[i][j].setState(CellState.BurningTree);
                    newCells[i][j].setBurningTime(newCells[i][j].getBurningTime()+10);
                }
                else
                    newCells[i][j] = matrix[i][j];
                newCells[i][j].isBurned();
            }
        matrix = newCells;
    }
}