package com.krzysztgac.forestfire.data;

import com.krzysztgac.forestfire.forest.CellState;
import com.krzysztgac.forestfire.forest.Forest;

import javax.swing.*;
import java.awt.*;

public class JForestPanel extends JPanel {

    Forest forestData;

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
                        g2.setColor(Color.BLUE);
                        g2.fillRect(i + 1, j + 1, 1, 1);
                    }
                    else {
                        g2.setColor(Color.GREEN);
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