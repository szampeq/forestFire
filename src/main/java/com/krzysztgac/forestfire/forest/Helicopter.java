package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.Main;
import com.krzysztgac.forestfire.states.CellState;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

import static com.krzysztgac.forestfire.Main.forestData;

public abstract class Helicopter{

    private static final int dropArea = 30;
    public static double waterAmount = 100.0;
    private static final double tankSize = 100.0;


    public static void dropWater(int x, int y) throws InterruptedException {
        try {
            if (waterAmount == tankSize) {
                for (int i = -dropArea; i < dropArea; i++)
                    for (int j = -dropArea; j < dropArea; j++) {

                        if (x + i < 0 || y + i < 0) continue;
                        if (x + i > forestData.matrix.length - 1 || y + j > forestData.matrix[0].length - 1) continue;

                        if (forestData.matrix[x + i][y + j].getState().equals(CellState.BurningTree)) {
                            forestData.matrix[x + i][y + j].setBurningTime(0);
                        } else
                            forestData.matrix[x + i][y + j].setBurningTime(forestData.matrix[x + i][y + i].getBurningTime() - 5);

                        waterAmount = 0.0;
                    }
            }
            refill();
        } catch (IndexOutOfBoundsException ignored) {}
    }

    static void refill() throws InterruptedException {
        while (waterAmount < tankSize) {
            TimeUnit.MILLISECONDS.sleep(20);
            waterAmount += 1.0;
            Main.waterAmountBar.setValue((int)waterAmount);
            if (waterAmount > tankSize)
                waterAmount = tankSize;
        }
    }

}
