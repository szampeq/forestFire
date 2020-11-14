package com.krzysztgac.forestfire;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(String title){
        super(title);

        // =================================


        // ============ PROGRAM SETTINGS =========


        // =========== MAIN PANEL ==========
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setSize(new Dimension(820, 710));
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {

        Main mw = new Main("Forest Fire");
        mw.setVisible(true);
    }

}
