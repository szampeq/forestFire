package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.*;

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


    public double getSpreadingProbability() {
        return spreadingProbability;
    }

    public void setSpreadingProbability(double spreadingProbability) {
        this.spreadingProbability = spreadingProbability;
    }

    public void setupForest() {

    }

}