package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.*;



public abstract class CalculateFactors {

    private static final double VERY_LOW_TRANSITION = 0.15;
    private static final double LOW_TRANSITION = 0.30;
    private static final double MEDIUM_TRANSITION = 0.50;
    private static final double HIGH_TRANSISTION = 0.70;
    private static final double VERY_HIGH_TRANSITION = 0.85;

    private static final int VERY_SLOW_SPREAD = 5;
    private static final int SLOW_SPREAD = 2;
    private static final int MEDIUM_SPREAD = 0;
    private static final int FAST_SPREAD = -3;
    private static final int VERY_FAST_SPREAD = -5;

    static double calculateSpreadingFireFactor(
            ForestState forestState, DensityState densityState, SeasonState seasonState, WindState windState,
            WindForceState windForceState, RainfallState rainfallState, HumidityState humidityState) {

        return 0.0;
    }

    static double burningTimeFactor(
            ForestState forestState, DensityState densityState, SeasonState seasonState, WindState windState,
            WindForceState windForceState, RainfallState rainfallState, HumidityState humidityState) {

        return 0.0;
    }
}
