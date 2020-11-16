package com.krzysztgac.forestfire.forest;

import com.krzysztgac.forestfire.states.WindState;

public abstract class WindMatrix {

    int[][] NorthWest = {
            {1, 1, 0},
            {1, 0, 0},
            {0, 0, 0}
    };
    int[][] North = {
            {1, 1, 1},
            {0, 0, 0},
            {0, 0, 0}
    };
    int[][] NorthEast = {
            {0, 1, 1},
            {0, 0, 1},
            {0, 0, 0}
    };
    int[][] East = {
            {0, 0, 1},
            {0, 0, 1},
            {0, 0, 1}
    };
    int[][] SouthEast = {
            {0, 0, 0},
            {0, 0, 1},
            {0, 1, 1}
    };
    int[][] South = {
            {0, 0, 0},
            {0, 0, 0},
            {1, 1, 1}
    };
    int[][] SouthWest = {
            {0, 0, 0},
            {1, 0, 0},
            {1, 1, 0}
    };
    int[][] West = {
            {1, 0, 0},
            {1, 0, 0},
            {1, 0, 0}
    };
    int[][] Windless = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    int[][] windMatrix(WindState windState) {
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

}
