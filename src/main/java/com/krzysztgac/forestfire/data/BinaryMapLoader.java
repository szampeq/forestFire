package com.krzysztgac.forestfire.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BinaryMapLoader {
    public BufferedImage map;

    public void loadImage(JForestPanel forestPanel, String path){
        try {
            map = ImageIO.read(new File(path));
            System.out.println("Mapa wczytana");
        } catch (IOException f) {
            f.printStackTrace();
        }
        forestPanel.repaint();
    }
}
