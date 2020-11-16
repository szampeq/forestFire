package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.*;

import java.util.concurrent.ThreadLocalRandom;

import static com.krzysztgac.forestfire.forest.CalculateFactors.calculateBurningTimeFactor;
import static com.krzysztgac.forestfire.forest.CalculateFactors.calculateSpreadingFireFactor;
import static com.krzysztgac.forestfire.forest.WindMatrix.windMatrix;

public class Forest {
    public Cell[][] matrix;
    double spreadingFireFactor;
    double burningTimeFactor;
    int[][] windDirection;

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

        spreadingFireFactor = calculateSpreadingFireFactor(densityState, seasonState, windForceState,
                rainfallState, humidityState);

        burningTimeFactor = calculateBurningTimeFactor(densityState, seasonState, windForceState,
                rainfallState, humidityState);

        windDirection = windMatrix(windState);

        System.out.println(spreadingFireFactor);
        System.out.println(burningTimeFactor);
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
                    matrix[x + i][y + j].setBurningTime(15 + burningTimeFactor);
                }
            }
    }

    public void cellNeighborhood() {

        // == Structure to compare and update matrix safely ==
        Cell[][] newCells = new Cell[matrix.length][matrix[0].length];
        for (int i = 0; i < newCells.length; i++)
            for (int j = 0; j < newCells[0].length; j++) {
                newCells[i][j] = new Cell(CellState.None);
                newCells[i][j].setBurningTime(matrix[i][j].getBurningTime());
            }

        // == Cell loop, step by step ==
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {

                // counting burning neighbors to calclulate spread fire chance
                int burningNeighbors = 0;

                // ======= neighbors loop =======
                for (int m = -1; m <= 1; m++)
                    for (int n = -1; n <= 1; n++) {

                        int x = i + m;
                        int y = j + n;

                        // exceptions
                        if (x == i && y == j) continue;
                        if (x < 0 || y < 0) continue;
                        if (x > matrix.length - 1 || y > matrix[0].length - 1) continue;

                        if (matrix[x][y].getState().equals(CellState.BurningTree))
                            burningNeighbors++;

                    }

                // checking neighbors
                double chanceOfSpread = burningNeighbors == 0 ? 0 : 0.5 * (spreadingFireFactor + burningNeighbors/8.0);

                // set a fire
                if (matrix[i][j].isFlammable() && chanceOfSpread > 0) {
                    double currentChance = ThreadLocalRandom.current().nextDouble(0, 1);
                    if (currentChance < chanceOfSpread) {
                        newCells[i][j].setState(CellState.BurningTree);
                        newCells[i][j].setBurningTime(newCells[i][j].burningTime += burningTimeFactor);
                    } else
                        newCells[i][j] = matrix[i][j];
                } else
                    newCells[i][j] = matrix[i][j];

                newCells[i][j].isBurned();
            }
        newCells = windEffect(newCells);
        matrix = newCells;
    }



    Cell[][] windEffect(Cell[][] cells) {

        Cell[][] newCells = new Cell[cells.length][cells[0].length];
        for (int i = 0; i < newCells.length; i++)
            for (int j = 0; j < newCells[0].length; j++) {
                newCells[i][j] = new Cell(CellState.None);
                newCells[i][j].setBurningTime(cells[i][j].getBurningTime());
            }

        int M = 0, N = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if(windDirection[i][j] == 1) {
                    M = i - 1;
                    N = j - 1;
                }

        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[0].length; j++) {

                int x = i + N;
                int y = j + M;

                if (x == i && y == j) continue;
                if (x < 0 || y < 0) continue;
                if (x > matrix.length - 1 || y > matrix[0].length - 1) continue;

                if (cells[i][j].getState().equals(CellState.BurningTree)) {
                    newCells[x][y].setState(CellState.BurningTree);
                } else
                    newCells[x][y] = cells[x][y];
            }
        return newCells;
    }
}