package com.krzysztgac.forestfire.data;

import com.krzysztgac.forestfire.forest.Cell;
import com.krzysztgac.forestfire.forest.CellState;

import java.awt.*;

public class Map {

    BinaryMapLoader bml;
    int width;
    int height;

    public Map(BinaryMapLoader bml) {
        this.bml = bml;
        this.width = bml.map.getWidth();
        this.height = bml.map.getHeight();
    }

    public Cell[][] ImageToMatrix() {

        Cell[][] forestMatrix = new Cell[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                forestMatrix[i][j] = new Cell(CellState.None);

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){

                Color readColor = new Color(bml.map.getRGB(i, j));
                int R = readColor.getRed();

                if (R > 50) {
                    forestMatrix[i][j].setState(CellState.LeafyTree);
                } else {
                    forestMatrix[i][j].setState(CellState.Lake);
                }

            }
        }
        return forestMatrix;
    }

}
