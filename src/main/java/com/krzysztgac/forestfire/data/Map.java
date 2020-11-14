package com.krzysztgac.forestfire.data;

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

    }
}
