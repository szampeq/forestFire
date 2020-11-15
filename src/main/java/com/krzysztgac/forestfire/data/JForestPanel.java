package com.krzysztgac.forestfire.data;

import com.krzysztgac.forestfire.states.CellState;
import com.krzysztgac.forestfire.forest.Forest;
import com.krzysztgac.forestfire.states.SeasonState;

import javax.swing.*;
import java.awt.*;

public class JForestPanel extends JPanel {

    Forest forestData;
    Color noneColor = new Color(190, 190, 190);
    Color coniferColor = new Color(40, 130, 60);
    Color leafyColor; // = new Color(60, 185, 85);
    Color burningColor = new Color(240, 105, 0);
    Color burnedColor = new Color(40, 30, 5);
    Color lakeColor = new Color(15, 115, 200);
    Color grassColor; // = new Color(140, 215, 0);

    public JForestPanel(Forest forestData) {
        this.forestData = forestData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (forestData.matrix != null) {
            int XS = forestData.matrix.length;
            int YS = forestData.matrix[0].length;
            Graphics2D g2 = (Graphics2D) g;
            setColors();

            for (int j = 0; j < YS; j++) {
                for (int i = 0; i < XS; i++) {
                    if (forestData.matrix[i][j].getState() == CellState.Lake)
                        g2.setColor(lakeColor);
                    else if (forestData.matrix[i][j].getState() == CellState.Grass)
                        g2.setColor(grassColor);
                    else if (forestData.matrix[i][j].getState() == CellState.LeafyTree)
                        g2.setColor(leafyColor);
                    else if (forestData.matrix[i][j].getState() == CellState.ConiferTree)
                        g2.setColor(coniferColor);
                    else if (forestData.matrix[i][j].getState() == CellState.BurningTree)
                        g2.setColor(burningColor);
                    else if (forestData.matrix[i][j].getState() == CellState.BurnedTree)
                        g2.setColor(burnedColor);
                    else
                        g2.setColor(noneColor);

                    g2.fillRect(i + 1, j + 1, 1, 1);
                }
            }
            super.repaint();
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    public void setColors() {

        if (forestData.getSeasonState() != null) {
            if (forestData.getSeasonState().equals(SeasonState.Spring)) {
                grassColor = new Color(16, 213, 0);
                leafyColor = new Color(157, 244, 11);
            } else if (forestData.getSeasonState().equals(SeasonState.Summer)) {
                grassColor = new Color(16, 213, 0);
                leafyColor = new Color(0, 128, 0);
            } else if (forestData.getSeasonState().equals(SeasonState.Autumn)) {
                grassColor = new Color(154, 240, 145);
                leafyColor = new Color(253, 253, 30);
            } else {
                grassColor = new Color(235, 235, 235);
                leafyColor = new Color(121, 107, 6);
            }
        }
    }
}