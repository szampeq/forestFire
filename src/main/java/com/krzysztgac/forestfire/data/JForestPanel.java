package com.krzysztgac.forestfire.data;

import javax.swing.*;
import java.awt.*;

public class JForestPanel extends JPanel {

    BinaryMapLoader bml;

    public JForestPanel(BinaryMapLoader bml){
        this.bml = bml;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bml.map, 0, 0, this);
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}