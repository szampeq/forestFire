package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.WindForceState;
import com.krzysztgac.forestfire.states.WindState;

public abstract class WindMatrix {

    private static final int[][] NorthWest = {
            {1, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    private static final int[][] North = {
            {0, 1, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    private static final int[][] NorthEast = {
            {0, 0, 1},
            {0, 0, 0},
            {0, 0, 0}
    };
    private static final int[][] East = {
            {0, 0, 0},
            {0, 0, 1},
            {0, 0, 0}
    };
    private static final int[][] SouthEast = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 1}
    };
    private static final int[][] South = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 1, 0}
    };
    private static final int[][] SouthWest = {
            {0, 0, 0},
            {0, 0, 0},
            {1, 0, 0}
    };
    private static final int[][] West = {
            {0, 0, 0},
            {1, 0, 0},
            {0, 0, 0}
    };
    private static final int[][] Windless = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    static int[][] windMatrix(WindState windState) {
        switch (windState) {
            case NorthWest:
                return NorthWest;
            case North:
                return North;
            case NorthEast:
                return NorthEast;
            case East:
                return East;
            case SouthEast:
                return SouthEast;
            case South:
                return South;
            case SouthWest:
                return SouthWest;
            case West:
                return West;
            default:
                return Windless;
        }
    }

    static double windForce(WindForceState windForceState) {
        switch (windForceState) {
            case GentleWind:
                return 0.2;
            case MediumWind:
                return 0.5;
            case StrongWind:
                return 0.8;
            default:
                return 0.0;
        }
    }

}
