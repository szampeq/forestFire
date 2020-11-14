package com.krzysztgac.forestfire.data;

import com.krzysztgac.forestfire.states.CellState;
import com.krzysztgac.forestfire.forest.Forest;

import javax.swing.*;
import java.awt.*;

public class JForestPanel extends JPanel {

    Forest forestData;
    Color noneColor = new Color(190, 190, 190);
    Color grassColor = new Color(140, 215, 0);
    Color coniferColor = new Color(40, 130, 60);
    Color leafyColor = new Color(60, 185, 85);
    Color burningColor = new Color(240, 105, 0);
    Color burnedColor = new Color(40, 30, 5);
    Color lakeColor = new Color(15, 115, 200);

    public JForestPanel(Forest forestData){
        this.forestData = forestData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (forestData.matrix != null) {
            int XS = forestData.matrix.length;
            int YS = forestData.matrix[0].length;
            Graphics2D g2 = (Graphics2D) g;


            for (int j = 0; j < YS; j++) {
                for (int i = 0; i < XS; i++) {
                    if (forestData.matrix[i][j].getState() == CellState.Lake) {
                        g2.setColor(lakeColor);
                        g2.fillRect(i + 1, j + 1, 1, 1);
                    }
                    else {
                        g2.setColor(grassColor);
                        g2.fillRect(i + 1, j + 1, 1, 1);
                    }
                }
            }
            super.repaint();
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}