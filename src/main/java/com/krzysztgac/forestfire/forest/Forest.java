package com.krzysztgac.forestfire.forest;

public class Forest {
    public Cell[][] matrix;
    int humidity;
    double spreadingProbability;

    public Forest() {
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getSpreadingProbability() {
        return spreadingProbability;
    }

    public void setSpreadingProbability(double spreadingProbability) {
        this.spreadingProbability = spreadingProbability;
    }

    public void updateForest() {

    }

}