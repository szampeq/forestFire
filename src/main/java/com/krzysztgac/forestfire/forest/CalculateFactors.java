package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.*;

public abstract class CalculateFactors {

    private static final int NUMBER_OF_STATES = 5;

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
            DensityState densityState, SeasonState seasonState,
            WindForceState windForceState, RainfallState rainfallState, HumidityState humidityState) {

        double spreadingFireFactor = 0.0;

        // DENSINITY STATE
        switch (densityState) {
            case Poorly:
                spreadingFireFactor += VERY_LOW_TRANSITION;
                break;
            case Medium:
                spreadingFireFactor += MEDIUM_TRANSITION;
                break;
            case Strongly:
                spreadingFireFactor += VERY_HIGH_TRANSITION;
                break;
            default:
                spreadingFireFactor += 0.0;
                break;
        }
    // SEASON STATE
        switch (seasonState) {
            case Spring:
                spreadingFireFactor += LOW_TRANSITION;
                break;
            case Summer:
                spreadingFireFactor += HIGH_TRANSISTION;
                break;
            case Autumn:
                spreadingFireFactor += VERY_HIGH_TRANSITION;
                break;
            case Winter:
                spreadingFireFactor += VERY_LOW_TRANSITION;
                break;
            default:
                spreadingFireFactor += 0.0;
                break;
        }
// WIND FORCE STATE
        switch (windForceState) {
            case GentleWind:
                spreadingFireFactor += LOW_TRANSITION;
                break;
            case MediumWind:
                spreadingFireFactor += MEDIUM_TRANSITION;
                break;
            case StrongWind:
                spreadingFireFactor += VERY_HIGH_TRANSITION;
                break;
            default:
                spreadingFireFactor += 0.0;
                break;
        }
// RAINFALL STATE
        switch (rainfallState) {
            case Drizzle:
                spreadingFireFactor += HIGH_TRANSISTION;
                break;
            case MediumRain:
                spreadingFireFactor += MEDIUM_TRANSITION;
                break;
            case HeavyRain:
                spreadingFireFactor += VERY_LOW_TRANSITION;
                break;
            default:
                spreadingFireFactor += 0.0;
                break;
        }
    // HUMIDITY STATE
        switch (humidityState) {
            case LowHumidity:
                spreadingFireFactor += VERY_HIGH_TRANSITION;
                break;
            case MediumHumidity:
                spreadingFireFactor += MEDIUM_TRANSITION;
                break;
            case HighHumidity:
                spreadingFireFactor += VERY_LOW_TRANSITION;
                break;
            default:
                spreadingFireFactor += 0.0;
                break;
        }

        return spreadingFireFactor/(double)NUMBER_OF_STATES;
    }

    static double calculateBurningTimeFactor(
            DensityState densityState, SeasonState seasonState,
            WindForceState windForceState, RainfallState rainfallState, HumidityState humidityState) {

        double burningTimeFactor = 0.0;

        // DENSINITY STATE
        switch (densityState) {
            case Poorly:
                burningTimeFactor += SLOW_SPREAD;
                break;
            case Medium:
                burningTimeFactor += MEDIUM_SPREAD;
                break;
            case Strongly:
                burningTimeFactor += FAST_SPREAD;
                break;
            default:
                burningTimeFactor += 0.0;
                break;
        }
        // SEASON STATE
        switch (seasonState) {
            case Spring:
                burningTimeFactor += SLOW_SPREAD;
                break;
            case Summer:
                burningTimeFactor += VERY_FAST_SPREAD;
                break;
            case Autumn:
                burningTimeFactor += FAST_SPREAD;
                break;
            case Winter:
                burningTimeFactor += VERY_SLOW_SPREAD;
                break;
            default:
                burningTimeFactor += 0.0;
                break;
        }
// WIND FORCE STATE
        switch (windForceState) {
            case GentleWind:
                burningTimeFactor += SLOW_SPREAD;
                break;
            case MediumWind:
                burningTimeFactor += MEDIUM_SPREAD;
                break;
            case StrongWind:
                burningTimeFactor += FAST_SPREAD;
                break;
            default:
                burningTimeFactor += 0.0;
                break;
        }
// RAINFALL STATE
        switch (rainfallState) {
            case Drizzle:
                burningTimeFactor += FAST_SPREAD;
                break;
            case MediumRain:
                burningTimeFactor += MEDIUM_SPREAD;
                break;
            case HeavyRain:
                burningTimeFactor += VERY_SLOW_SPREAD;
                break;
            default:
                burningTimeFactor += VERY_FAST_SPREAD;
                break;
        }
        // HUMIDITY STATE
        switch (humidityState) {
            case LowHumidity:
                burningTimeFactor += VERY_FAST_SPREAD;
                break;
            case MediumHumidity:
                burningTimeFactor += MEDIUM_SPREAD;
                break;
            case HighHumidity:
                burningTimeFactor += VERY_SLOW_SPREAD;
                break;
            default:
                burningTimeFactor += 0.0;
                break;
        }

        return burningTimeFactor/(double)NUMBER_OF_STATES;
    }
}
