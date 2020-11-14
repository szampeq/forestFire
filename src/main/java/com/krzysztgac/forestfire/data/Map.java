package com.krzysztgac.forestfire.data;

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

    public void createForest() {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){

                Color readColor = new Color(bml.map.getRGB(i, j));
                int R = readColor.getRed();
                int G;
                int B;

                if (R > 50) {
                    R = 70;
                    G = 220;
                    B = 10;
                } else {
                    R = 20;
                    G = 120;
                    B = 220;
                }

                int RGB = new Color(R, G, B).getRGB();
                bml.map.setRGB(i, j, RGB);
            }
        }
    }
}
